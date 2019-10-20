import org.scalatest.{ FlatSpec, Matchers }
import org.scalatest.OptionValues

/**
 * `value` method allows you to express in one statement that:
 *   - an option should be defined and
 *   - that option's value should meet some expectation
 */

class OptionValuesTraitExampleSpec extends FlatSpec with Matchers with OptionValues {

  val opt: Option[Int] = Some(10)

  "Trait's `value` method" should "allow to get result with a stack depth exception in one statement" in {
    opt.value should be > 9
  }

  "Without this trait's method" should "allow to get result with a stack depth exception in two statement" in {
    opt should be ('defined) // throws 'TestFailedException' if option wasn't defined
    opt.get should be > 9
  }
}
