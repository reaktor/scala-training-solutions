package survival

import scala.collection.JavaConversions.seqAsJavaList

sealed abstract class Animal {
  def name: String
  def age: Int
  def weight: Int
}

case class Wolf(val name: String, val age: Int, val weight: Int) extends Animal
case class Lamb(val name: String, val age: Int, val weight: Int) extends Animal

trait Population {
  def nextGeneration: Population

  def animals: List[Animal]
}

case class ScalaPopulation(myAnimals: List[Animal]) extends Population {
  def nextGeneration: Population = {
    ScalaPopulation(ScalaPopulation.next(myAnimals))
  }

  def animals: List[Animal] = {
    myAnimals
  }
}

object ScalaPopulation {
  /**
   * The next stage of evolution in the given animal population.
   */
  def next(animals: List[Animal]): List[Animal] = {
    animals match {
      // The earth is void and empty.
      case Nil => Nil
      // A lone wolf starves to death.
      case Wolf(name, age, weight) :: Nil => Nil
      // A lone lamb lives and grows happily for ever.
      case Lamb(name, age, weight) :: Nil => Lamb(name, age + 1, weight + 1) :: Nil
      // Two wolves fight each other. The bigger one wins.
      case Wolf(name1, age1, weight1) :: Wolf(name2, age2, weight2) :: rest =>
        (if (weight1 > weight2) Wolf(name1, age1 + 1, weight1) else Wolf(name2, age2 + 1, weight2)) :: next(rest)
      // A wolf eats a lamb.
      case Wolf(name, age, wolfWeight) :: Lamb(_, _, lambWeight) :: rest => Wolf(name, age + 1, wolfWeight + lambWeight) :: next(rest)
      // A lamb gets eaten by a wolf.
      case Lamb(_, _, lambWeight) :: Wolf(name, age, wolfWeight) :: rest => Wolf(name, age + 1, wolfWeight + lambWeight) :: next(rest)
      // A lamb who is not yet eaten by a wolf grows.
      case Lamb(name, age, weight) :: rest => Lamb(name, age + 1, weight + 1) :: next(rest)
    }
  }
}