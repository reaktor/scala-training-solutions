package sticks

import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class ImplicitSticksSpec extends FlatSpec {
  "Integer" should "convert implicitly to Sticks" in {
    import ImplicitSticks._
    assert(4.sticks == "||||")
  }
}