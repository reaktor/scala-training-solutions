package wordcount

class Chapter(val title: Title, val paragraphs: Seq[Paragraph]) extends DatabaseEntity with WordCountable {
  def content = {
    paragraphs.map(_.content).mkString(" ") ++ " " ++ title.content
  }
}