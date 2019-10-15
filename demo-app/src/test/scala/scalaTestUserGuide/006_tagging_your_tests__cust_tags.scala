 /**
  * how to run:
  *
  * mvn test -DtagsToInclude="Slow,DbTest"
  *
  * For more information:
  *   http://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
  *
  */

import org.scalatest.Tag
object DbTest extends Tag("com.mycompany.tags.DbTest")

import org.scalatest.FlatSpec
import org.scalatest.tagobjects.Slow

class ExampleSpec extends FlatSpec {

  "The Scala language" must "add correctly" taggedAs(Slow) in {
      val sum = 1 + 1
      assert(sum === 2)
    }

  it must "subtract correctly" taggedAs(Slow, DbTest) in {
    val diff = 4 - 1
    assert(diff === 3)
  }
}
