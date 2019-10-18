/**
 * Generator-driven property check example
 */

import scalaTestUserGuide.Fraction
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

class GeneratorDrivenPropertyCheckExample extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {

  "All combinations of valid values" should "be properly normalized" in {
    forAll { (n: Int, d: Int) =>

      whenever (d != 0 && d != Integer.MIN_VALUE
          && n != Integer.MIN_VALUE) {

        val f = new Fraction(n, d)

        if (n < 0 && d < 0 || n > 0 && d > 0)
          f.numer should be > 0
        else if (n != 0)
          f.numer should be < 0
        else
          f.numer should be === 0

        f.denom should be > 0
      }
    }
  }
}
