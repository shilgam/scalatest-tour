import org.scalatest.{ FlatSpec, Matchers }
import org.scalamock.scalatest.MockFactory

class ScalaMockExampleSpec extends FlatSpec with Matchers with MockFactory {

  behavior of "ScalaMock"

  it should "allow to mock functions" in {
    val mock = mockFunction[Int, String]

    // Setup your mock to be called once with the argument 42
    mock expects (42) returning "Forty two" once

    assert(mock(42) === "Forty two")
  }
}
