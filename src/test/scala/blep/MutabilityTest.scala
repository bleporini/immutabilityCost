package blep

import org.scalatest.FunSuite

/**
 *
 * User: blep
 */
class MutabilityTest extends FunSuite{

  case class Mutable(var date:Long)

  val config=Map('debug->false)

  test("Mutable transformation"){
    def bench=_bench(config,generate(()=>Mutable(System.nanoTime())))_

    def addOneDay(from:Mutable):Mutable={
      from.date = from.date + oneDayNs
      from
    }
    def bencher = bench(addOneDay)

    bencher(100000,10000)

  }




}


