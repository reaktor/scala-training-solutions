package initials

/**
 * Initiallable is a trait for translating a sequence of parts
 * into various combinations of initials and expanded words.
 *
 * E.g. "John Fitzgerald Kennedy" could be initialled as either
 * "J.F.K." (initials), "John F. Kennedy" (middle initials) or
 * "J.F. Kennedy" (first initials).
 */
trait Initiallable {

  /**
   * 'parts' is an abstract method required from the object extending this trait.
   */
  def parts: Seq[String]

  /**
   * Formats the parts by initialling everything but the last part.
   */
  def firstInitials: String = {
    val last = lastName.mkString("")
    val initials = (firstName ++ middleNames).map(initial).mkString("")
    "%s %s".format(initials, last).trim
  }

  /**
   * Formats the parts by initialling everything but the first and last part.
   */
  def middleInitials: String = {
    val last = lastName.mkString("")
    val first = firstName.mkString("")
    val middle = middleNames.map(initial).mkString("")
    val firstAndMiddle = "%s %s".format(first, middle).trim
    "%s %s".format(firstAndMiddle, last).trim
  }

  /**
   * Formats the parts by initialling each of them.
   */
  def initials: String = {
    parts.map(initial).mkString("")
  }

  private def firstName: Seq[String] = { parts.dropRight(1).take(1) }
  private def lastName: Seq[String] = { parts.takeRight(1) }
  private def middleNames: Seq[String] = { parts.dropRight(1).drop(1) }
  private def initial(word: String) = { "%s.".format(word.charAt(0)) }
}