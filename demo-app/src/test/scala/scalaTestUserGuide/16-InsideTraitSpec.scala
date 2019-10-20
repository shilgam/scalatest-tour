/**
 * Case classes under test
 */
case class Address(street: String, city: String, state: String, zip: String)
case class Name(first: String, middle: String, last: String)
case class Record(name: Name, address: Address, age: Int)

/**
 * 'Inside' trait contains an `inside` construct, which allows you to make
 * statements about nested object graphs using pattern matching.
 */
import org.scalatest.{ FlatSpec, Matchers }
import org.scalatest.Inside

class UsingInsideExampleSpec extends FlatSpec with Matchers with Inside {

  "Inside trait" should "allows to make to check nested object graphs" in {
    val rec = Record(
      Name("Sally", "Anna", "Jones"),
      Address("25 Main St", "Los Angeles", "CA", "12345"),
      38
    )

    inside (rec) { case Record(name, address, age) =>
      inside (name) { case Name(first, middle, last) =>
        first should be ("Sally")
        middle should be ("Anna")
        last should be ("Jones")
      }
      inside (address) { case Address(street, city, state, zip) =>
        street should startWith ("25")
        city should endWith ("Angeles")
        state should equal ("CA")
        zip should be ("12345")
      }
      age should be < 99
    }
  }
}
