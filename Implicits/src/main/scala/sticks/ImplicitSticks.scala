package sticks

object ImplicitSticks {
  implicit def int2Sticks(number: Int): Sticks = {
    new Sticks(number)
  }
}
