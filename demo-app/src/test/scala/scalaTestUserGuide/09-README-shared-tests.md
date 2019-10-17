# Sharing tests

src: http://www.scalatest.org/user_guide/sharing_tests

Sometimes you may want to write tests that are "shared" by different fixture objects. To accomplish this in a FlatSpec you first place shared tests in behavior functions. These behavior functions will be invoked during the construction phase of any FlatSpec that uses them, so that the tests they contain will be registered as tests in that FlatSpec.

[Example in functional style](09-StackSpec.scala)

Notes:
- each test in a suite must have a unique name. So keep in mind when using shared tests is that in ScalaTest
- Shared tests are supported in all style traits in which tests are represented as functions.
    - In trait Suite tests are methods, thus shared tests aren't supported.
- A related technique is property-based testing. Whereas in shared tests you evaluate the same test function on different data, in property-based testing you evaluate the same property function on different data.
