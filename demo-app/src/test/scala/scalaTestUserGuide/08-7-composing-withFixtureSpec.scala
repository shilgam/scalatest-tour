/**
 * By mixing in both the Builder and Buffer traits, ExampleSuite gets both fixtures,
 * which will be initialized before each test and cleaned up after.
 * The order the traits are mixed together determines the order of execution.
 * In this case, Builder is “super” to Buffer.
 * If you wanted Buffer to be “super” to Builder, you need only switch the order
 * you mix them together, like this:
 *
 * `class Example2Suite extends Suite with Buffer with Builder`
 *
 */

package org.scalatest.examples.flatspec.composingwithfixture

import org.scalatest._
import collection.mutable.ListBuffer

trait Builder extends TestSuiteMixin { this: TestSuite =>

  val builder = new StringBuilder

  abstract override def withFixture(test: NoArgTest) = {
    builder.append("ScalaTest is ")
    try super.withFixture(test) // To be stackable, must call super.withFixture
    finally builder.clear()
  }
}

trait Buffer extends TestSuiteMixin { this: TestSuite =>

  val buffer = new ListBuffer[String]

  abstract override def withFixture(test: NoArgTest) = {
    try super.withFixture(test) // To be stackable, must call super.withFixture
    finally buffer.clear()
  }
}

class StackableTraitsExampleSpec extends FlatSpec with Builder with Buffer {

  "Testing" should "be easy" in {
    builder.append("easy!")
    assert(builder.toString === "ScalaTest is easy!")
    assert(buffer.isEmpty)
    buffer += "sweet"
  }

  it should "be fun" in {
    builder.append("fun!")
    assert(builder.toString === "ScalaTest is fun!")
    assert(buffer.isEmpty)
    buffer += "clear"
  }
}
