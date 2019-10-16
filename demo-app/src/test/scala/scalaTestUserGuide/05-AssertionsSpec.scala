
import org.scalatest._

class ScalaTestAssertionsSpec extends FlatSpec {
  "A scala assert" should "complete with an TestFailedException" in {

    assertThrows[org.scalatest.exceptions.TestFailedException] {
      assert(1 == 2)
    }
  }
}
