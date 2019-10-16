/**
 * An example in which withFixture(NoArgTest) is used to take a snapshot
 * of the working directory if a test fails, and send that information to the reporter
 */

package org.scalatest.examples.flatspec.noargtest

import java.io.File
import org.scalatest._

class withFixtureNoArgTestExampleSpec extends FlatSpec {

  override def withFixture(test: NoArgTest) = {

    super.withFixture(test) match {
      case failed: Failed =>
        val currDir = new File(".")
        val fileNames = currDir.list()
        info("Dir snapshot: " + fileNames.mkString(", "))
        failed
      case other => other
    }
  }

  ignore should "fail" in { // replace 'ignore' with 'it' to see demo
    assert(1 + 1 === 3)
  }
}
