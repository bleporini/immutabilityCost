import scala.annotation.tailrec

/**
 *
 * User: blep
 */

package object blep {

  val oneDayNs=24*60*60*1000*1000l


  def _bench[A,B](settings:Map[Symbol,Boolean],generator:Int=>Seq[A])
                 (transformer:A=>B)(nbElems:Int,iterations:Int)= {
    val debug = settings('debug)

    val samples = 100
    val debugEvery=100

    import System.nanoTime

    val beforeGen = nanoTime
    val mo = generator(nbElems)
    val genTime = (nanoTime - beforeGen)/1000

    println("Data generation : " + genTime)

    @tailrec
    def doIt(iter:Int,times:List[Long]):List[Long]=
      if(iter==0) times
      else{
        val beforeTrans = nanoTime
        mo map transformer
        val chrono=(nanoTime - beforeTrans) / 1000
        if(debug && iter%debugEvery==0) println(s"${iterations-iter} nth time: $chrono us")
        doIt(iter-1,chrono::times)
      }
    val fullData = doIt(iterations, Nil)
    val fullAvg = fullData.foldLeft(0l)(_ + _) / fullData.length
    val sampledAvg = fullData.drop(iterations - samples).foldLeft(0l)(_ + _) / samples
    println(s"$iterations,${fullData.max},${fullData.min},$sampledAvg,$fullAvg")

  }

  def generate[A](generator:()=>A)(amount:Int):Seq[A]=
    for {i <- 0 to amount
        elem = generator()
    } yield elem

}
