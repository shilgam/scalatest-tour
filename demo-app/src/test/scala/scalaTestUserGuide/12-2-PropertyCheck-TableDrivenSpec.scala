/**
 * Table-driven property check example
 */

import scalaTestUserGuide.Fraction
import org.scalatest.{ FlatSpec, Matchers }
import org.scalatest.prop.TableDrivenPropertyChecks

class TableDrivenPropertyCheckExampleSpec extends FlatSpec with Matchers with TableDrivenPropertyChecks {

  "All combinations of invalid values" should "produce the exception" in {
    val invalidCombos =
      Table(
        ("n",               "d"),
        (Integer.MIN_VALUE, Integer.MIN_VALUE),
        (1,                 Integer.MIN_VALUE),
        (Integer.MIN_VALUE, 1),
        (Integer.MIN_VALUE, 0),
        (1,                 0)
      )

    forAll (invalidCombos) { (n: Int, d: Int) =>
      an [IllegalArgumentException] should be thrownBy {
        new Fraction(n, d)
      }
    }
  }
}
