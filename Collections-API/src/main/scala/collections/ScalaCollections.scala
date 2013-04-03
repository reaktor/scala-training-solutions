package collections

class ScalaCollections {
  def keepOnlyEvenNumbers(input: List[Int]): List[Int] = {
    input.filter(_ % 2 == 0)
  }

  def doubleEachNumber(input: List[Int]): List[Int] = {
    input.map(_ * 2)
  }

  def sortByAbsoluteValue(input: List[Int]): List[Int] = {
    input.sortBy(_.abs)
  }

  def groupByNumberOfDigits(input: List[Int]): Map[Int, List[Int]] = {
    input.groupBy(_.abs.toString.length())
  }

  def sumOfNumbers(input: List[Int]): Int = {
    // Scala lists have sum as a built-in function:
    input.sum
    // Here is how we could easily do it with a fold:
    // input.foldLeft(0)((accumulator, current) => accumulator + current)
  }

  def deltasOfNumbers(input: List[Int]): List[Int] = {
    // Accumulator is a tuple (List[Int], Integer) where the first member is the list of results and the second member is the previous number in the list.
    // So accumulator._1 is the list in which we collect the deltas and accumulator._2 is the previous number encountered.
    // The result of foldLeft is the accumulator tuple in the end, so _1 is the list of deltas.
    // We drop the first element from the result, because that is just the delta between 0 and the first member of the list.
    //
    // Example with input List(2, 5, 9):
    // STEP               ACCUMULATOR VALUE                 CURRENT ELEMENT
    // Initial state      (List(), 0)                       None
    // 1                  (List(2), 2)                      2
    // 2                  (List(2, 3), 5)                   5
    // 3                  (List(2, 3, 4), 9)                9
    //
    // And by dropping the first element from the accumulated list we get (3, 4) as the result. |2 - 5| = 3 and |5 - 9| = 4.
    input.foldLeft((List[Int](), 0))((accumulator, current) => (accumulator._1 :+ (current - accumulator._2).abs, current))._1.drop(1)
  }
}