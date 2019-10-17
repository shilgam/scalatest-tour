import org.scalatest.{ FlatSpec, Matchers }

class MatchersExampleSpec extends FlatSpec with Matchers {
  val result = 3

  "Checking equality" should "be available" in {
    assert(result === 3)

    result should equal (3) // can customize equality
    result should === (3)   // can customize equality and enforce type constraints
    result should be (3)    // cannot customize equality, so fastest to compile
    result shouldEqual 3    // can customize equality, no parentheses required
    result shouldBe 3       // cannot customize equality, so fastest to compile, no parentheses required

    Array(1, 2) should equal (Array(1, 2)) // compares object identity
    assertThrows[org.scalatest.exceptions.TestFailedException] {
      assert(Array(1, 2) == Array(1, 2)) // compares arrays structurally
    }
  }

  "Checking greater and less than" should "be available" in {
    result should be < 7
    result should be > 0
    result should be <= 7
    result should be >= 0
  }

  "Checking strings" should "be available" in {
    val string = "Hello my dear friend!"
    string should startWith ("Hello")
    string should include ("dear")
    string should endWith ("friend!")

    string should startWith regex "Hel*o"
    string should include regex "de.r"
    string should endWith regex "fr.end!"

  }

  "Checking Boolean props" should "be available with `be`" in {
    val emptySet: Set[Int] = Set()

    emptySet should be ('empty)
    emptySet shouldBe 'empty
    emptySet shouldBe empty
    emptySet should be an 'empty
  }

  "Checking numbers against a range" should "be available" in {
    val sevenDotOh = 7.0

    sevenDotOh should equal (6.9 +- 0.2)
    sevenDotOh should === (6.9 +- 0.2)
    sevenDotOh should be (6.9 +- 0.2)
    sevenDotOh shouldEqual 6.9 +- 0.2
    sevenDotOh shouldBe 6.9 +- 0.2
  }

  "Checking a collection contains an element(s)" should "be available" in {
    val list = List(1, 2, 3)
    list should contain (3)
    list should contain oneOf (3, 10, 20)
    list should contain noneOf (10, 20)
    list should contain atLeastOneOf (2, 10, 20)
    list should contain atMostOneOf (2, 10, 20)
    list should contain allOf (2, 3)

    List(1, 2, 1) should contain only (1, 2)
    List(0, 1, 2, 99, 3, 3) should contain inOrder (1, 2, 3)

    List(1, 2, 3) shouldBe sorted // working with "sortables"
  }

  "Checking that an expression" should "match a pattern" in {
    case class Name(first: String, middle: String, last: String)
    val name = Name("Jane", "Q", "Programmer")

    name should matchPattern { case Name("Jane", _, _) => }
  }

  // "Using custom matchers" should "be available" in {
  //   // file.getName should endWith (".txt")
  // }
}
