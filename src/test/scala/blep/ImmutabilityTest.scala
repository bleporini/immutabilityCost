package blep

import java.util.Date
import scala.compat.Platform
import scala.annotation.tailrec
import org.scalatest.FunSuite

/**
 *
 * User: blep
 */



class ImmutabilityTest extends FunSuite{

  case class Immutable(date:Long)

  val config=Map('debug->true)
//  val config=Map('debug->false)


  test("Immutable tranformation"){
    def bench=_bench(config,generate(()=>Immutable(System.nanoTime())))_

    def addOneDay(from:Immutable):Immutable= Immutable(from.date+oneDayNs)

    def bencher = bench(addOneDay)

    bencher(100000,10000)
//    bencher(10,10000)

  }



}
