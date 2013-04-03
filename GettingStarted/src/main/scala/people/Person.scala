package people

import scala.beans.BeanProperty

class Person(name: String, @BeanProperty var age: Int) {
  def nameWithoutLetter(letter: Char): String = {
    var result = name

    // "Java loop style":
    val s = new StringBuilder
    for (i <- 0 to (name.length - 1)) {
      if (name.charAt(i) != letter) {
        s.append(name.charAt(i))
      }
    }
    result = s.toString

    // Good ol' search-and-replace:
    result = name.replaceAll(letter.toString, "")

    // Scala style:
    result = name.filterNot(_ == letter)

    result
  }
}

object Person {
  val DefaultAge = 18
  def createWithDefaultAge(name: String): Person = new Person(name, DefaultAge)
}
