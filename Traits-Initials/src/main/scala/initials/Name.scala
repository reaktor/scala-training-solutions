package initials

class Name(val name: String) extends Initiallable {

  override def toString: String = name

  /**
   * This is all we need to implement to make ScalaName "initiallable"!
   */
  def parts: Seq[String] = { name.split(" ") }
}