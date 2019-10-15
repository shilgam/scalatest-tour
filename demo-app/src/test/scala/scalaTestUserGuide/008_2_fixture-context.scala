package org.scalatest.examples.flatspec.fixturecontext

import collection.mutable.ListBuffer
import org.scalatest.FlatSpec

class FixtureContextExampleSpec extends FlatSpec {

  trait Builder {
    val builder = new StringBuilder("ScalaTest is ")
  }

  // This test needs the StringBuilder fixture
  "Testing" should "be productive" in new Builder {
    builder.append("productive!")
    assert(builder.toString === "ScalaTest is productive!")
  }

  // This test needs both the StringBuilder and ListBuffer
  it should "be clear and concise" in new Builder {
    builder.append("clear!")
    assert(builder.toString === "ScalaTest is clear!")
  }
}
