/**
 * Class under test
 */
class Person {
  private def greeting(name: String) = "Hello! My name is " + name
}

import org.scalatest.{ FlatSpec, Matchers }
import org.scalatest.PrivateMethodTester
import org.scalatest._

/**
 * 'PrivateMethodTester' trait facilitates the testing of private methods
 * To test a private method you need:
 *   - mix in trait PrivateMethodTester and
 *   - create a PrivateMethod object
 */
class PrivateMethodTesterTraitExampleSpec extends FlatSpec with Matchers with PrivateMethodTester {

  "Trait's `valueAt` method" should "allow to do smth" in {
    val person = new Person()

    val greeting = PrivateMethod[String]('greeting)

    val result = person invokePrivate greeting("Andrew")

    result should be ("Hello! My name is Andrew")
  }
}
