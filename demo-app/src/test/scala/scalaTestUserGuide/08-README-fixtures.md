# Sharing fixtures
src: http://www.scalatest.org/user_guide/sharing_fixtures#getFixtureMethods

## 1. Refactor using Scala when different tests need different fixtures.

### 1.1. [get-fixture methods](08-1-GetFixtureSpec.scala)
- helps you create a fresh instances of mutable fixture objects in each test that needs them
- but doesn't help you clean them up when you're done

### 1.2. [fixture-context](08-2-FixtureContextSpec.scala)
- objects	By placing fixture methods and fields into traits, you can easily give each test just the newly created fixtures it needs by mixing together traits.
- Use this technique when you need different combinations of mutable fixture objects in different tests, and don't need to clean up after.

### 1.3. [loan-fixture methods](08-3-LoanFixtureSpec.scala)
You'll need to use this pattern if you need to both:
- a) pass a fixture object into a test
- b) and perform cleanup at the end of the test


## 2. Override `withFixture` when most or all tests need the same fixture.

- The recommended default approach when most or all tests need the same fixture treatment.
- This general technique allows you, for example, to perform side effects at the beginning and end of all or most tests.
- Use this technique **unless**:
    - Different tests need different fixtures
    - An exception in fixture code should abort the suite, not fail the test (use a before-and-after trait instead)

### 2.1. Overriding [`withFixture(NoArgTest)`](08-4-withFixture-NoArgTestSpec.scala)
- If you just need to perform a side-effect at the beginning or end of a test, and don't need to actually pass any fixture objects into the test, you can override `withFixture(NoArgTest)`.

### 2.2. Overriding [`withFixture(OneArgTest)`](08-5-withFixture-OneArgTestSpec.scala)
- Use when you want to pass the same fixture object (or objects) as a parameter into all or most tests.


## 3. Mix in a before-and-after trait when you want an aborted suite, not a failed test, if the fixture code fails.

### [Mixing in `BeforeAndAfter`](08-6-BeforeAndAfterSpec.scala)
- In all the shared fixture examples shown so far, the activities of creating, setting up, and cleaning up the fixture objects have been performed during the test. This means that if an exception occurs during any of these activities, it will be reported as a test failure.
- Sometimes, however, you may want setup to happen before the test starts, and cleanup after the test has completed, so that if an exception occurs during setup or cleanup, the entire suite aborts and no more tests are attempted.

Note, that `BeforeAndAfter` trait isn't designed to enable stackable traits, because the order of execution would be non-obvious.


## 4. Composing fixtures by stacking traits

In larger projects, teams often end up with several different fixtures that test classes need in different combinations, and possibly initialized (and cleaned up) in different orders.

A good way to accomplish this in ScalaTest is to factor the individual fixtures into traits that can be composed using **the stackable trait pattern**.

### 4.1. [`withFixture`](08-7-composing-withFixtureSpec.scala)
This can be done, for example, by placing `withFixture` methods in several traits, each of which call `super.withFixture`. Here's an example in which the 2 fixtures used in the previous examples (`StringBuilder` and `ListBuffer[String]`) have been factored out into two stackable fixture traits named `Builder` and `Buffer`.

### 4.2. [`BeforeAndAfterEach`](08-8-composing-BeforeAndAfterEachSpec.scala)
Another way to create stackable fixture traits is by extending the `BeforeAndAfterEach` and/or `BeforeAndAfterAll` traits:
- `BeforeAndAfterEach` has a `beforeEach` & `afterEach` methods that will be run before and after each test.
- Similarly, `BeforeAndAfterAll` has a `beforeAll`, `afterAll` methods that will be run before and after all tests.

See example: previous example that was rewritten to use the `BeforeAndAfterEach` methods instead of `withFixture`.

### Notes
- To get the same ordering as `withFixture`, place your `super.beforeEach` call at the end of each `beforeEach` method, and the `super.afterEach` call at the beginning of each `afterEach` method, as shown in the previous example.
- It is a good idea to invoke `super.afterEach` in a try block and perform cleanup in a finally clause, as shown in the previous example, because this ensures the cleanup code is performed even if `super.afterEach` throws an exception.

- What is the difference between `BeforeAndAfterEach` and `withFixture` traits?
    - in `BeforeAndAfterEach` trait setup and cleanup code happens before and after the test.
    - in `withFixture` trait setup and cleanup code happens at the beginning and end of the test. Thus if those methods completes abruptly with an exception, it is considered a failed test.
