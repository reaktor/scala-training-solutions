package people

import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class PersonSpec extends FlatSpec {
  val jack = new Person("Jack", 35)
  val jill = Person.createWithDefaultAge("Jill")

  "A Person created via constructor" should "have the age given as parameter" in {
    assert(jack.getAge === 35)
  }

  "A Person created via factory method" should "have the default age" in {
    assert(jill.getAge === 18)
  }

  "Any person" should "be able to provide its name without the specified letter" in {
    assert(jack.nameWithoutLetter('k') === "Jac")
    assert(jill.nameWithoutLetter('l') === "Ji")
  }

  it should "be able to change its age later" in {
    jack.setAge(14)
    assert(jack.getAge === 14)
  }
}
