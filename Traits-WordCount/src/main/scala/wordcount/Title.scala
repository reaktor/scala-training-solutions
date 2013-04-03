package wordcount

class Title(val title: String) extends DatabaseEntity with WordCountable {
	def content = title
	override def excludedWords = List("a", "an", "the")
}