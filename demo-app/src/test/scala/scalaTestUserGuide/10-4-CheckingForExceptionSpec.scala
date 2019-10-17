import org.scalatest.{ FlatSpec, Matchers }


class CheckingForExceptionExampleSpec extends FlatSpec with Matchers {
  "Checking for exception" should "be available" in {
    an [IndexOutOfBoundsException] should be thrownBy "hi!".charAt(-1)
  }

  "Inspecting an exception" should "be available" in {
    val thrown = the [IndexOutOfBoundsException] thrownBy "hi!".charAt(-1)

    thrown.getMessage should equal ("String index out of range: -1")
  }

  "Capturing and inspecting in one statement" should "be available" in {
    the [ArithmeticException] thrownBy 1 / 0 should have message "/ by zero"

    the [IndexOutOfBoundsException] thrownBy {
      "hi!".charAt(-1)
    } should have message "String index out of range: -1"
  }
}
