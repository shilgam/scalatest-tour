import org.scalatest.{ FlatSpec, Matchers }


class CustomMatchersUsingLogicalOperatorExampleSpec extends FlatSpec with Matchers {
  "Creating matchers using logical operators" should "be available" in {
    val beWithinTolerance = be >= 0 and be <= 10

    5 should beWithinTolerance
  }
}
