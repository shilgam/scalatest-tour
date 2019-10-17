import org.scalatest.{ FlatSpec, Matchers }
import java.io.File
import CustomMatchers._

class CustomMatcherExampleSpec extends FlatSpec with Matchers {
  val file = new File("essay.txt")

  "Standard matchers" should "be available" in {
    file.getName should endWith (".txt")
  }

  "Custom matchers" should "be available" in {
    file should endWithExtension ("txt")
  }
}
