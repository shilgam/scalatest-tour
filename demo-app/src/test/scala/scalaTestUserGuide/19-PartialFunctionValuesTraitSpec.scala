import org.scalatest.{ FlatSpec, Matchers }
import org.scalatest.PartialFunctionValues

class PartialFunctionValuesTraitExampleSpec extends FlatSpec with Matchers with PartialFunctionValues {

  val pf: PartialFunction[String, Int] = Map("I" -> 1, "II" -> 2, "III" -> 3, "IV" -> 4)

  "Trait's `valueAt` method" should "allow to get result with a stack depth exception in one statement" in {
    pf.valueAt("III") should equal (3)
  }

  "Without this trait's method" should "allow to get result with a stack depth exception in two statement" in {
    pf.isDefinedAt("III") should be (true) // throws TestFailedException
    pf("III") should equal (3)
  }
}
