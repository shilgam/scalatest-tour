import org.scalatest.{ FlatSpec, Matchers }
import org.scalatest.OptionValues

/**
 * `value` method allows you to express in one statement that:
 *   - an option should be defined and
 *   - that option's value should meet some expectation
 */
class OptionValuesTraitExampleSpec extends FlatSpec with Matchers with OptionValues {
  behavior of "With 'OptionValues' trait"
  it should "provide `value` method to get option's value if it is defined" in {
    val opt: Option[Int] = Some(10)

    opt.value should be > 9
  }
}

/**
 * Without 'OptionValues', to get a stack depth exception you would need
 * to make two statements:
 *
 * > opt should be ('defined)
 * > opt.get should be > 9
 */
class WithoutOptionValuesTraitExampleSpec extends FlatSpec with Matchers {
  behavior of "without OptionValues trait"
  it should "provide `value` method to get option's value if it is defined" in {
    val opt: Option[Int] = Some(10)

    opt should be ('defined) // throws 'TestFailedException' if option wasn't defined
    opt.get should be > 9
  }
}
