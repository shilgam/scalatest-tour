import org.scalatest.FlatSpec
import scala.collection.mutable.Stack

class TaggingSpec extends FlatSpec {

  "Tests tagged as 'ignore'" should "be ignored" in {
      val stack = new Stack[Int]
      stack.push(1)
      stack.push(2)
      assert(stack.pop() === 2)
      assert(stack.pop() === 1)
    }

  ignore should "not be launched" in {
    val emptyStack = new Stack[String]
    intercept[NoSuchElementException] {
      emptyStack.pop()
    }
  }
}
