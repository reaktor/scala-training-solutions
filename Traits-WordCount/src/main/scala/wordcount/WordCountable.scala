package wordcount

trait WordCountable {
  /**
   * Abstract method that returns the content from which to count words.
   */
  def content: String

  /**
   * Returns a list of words that are excluded from the word count.
   * By default no words are ignored - override this method if you
   * want to exclude some words from the word count.
   */
  def excludedWords: Seq[String] = { List() }

  /**
   * Count the number of words.
   */
  def wordCount: Int = {
    val words = content.split(" ").map(_.split("-")).flatten.map(_.toLowerCase)
    words.filterNot((w) => excludedWords.contains(w)).size
  }
}