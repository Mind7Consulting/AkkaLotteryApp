
import LotteryActor.StartLottery
import akka.actor.ActorSystem

object MainLottery extends App {
//  if (args.length == 0) {
//    println("dude, i need at least one parameter")
//    sys.exit(0)
//  }
//  val winners = args(0)
  val system = ActorSystem("Lottery")

  val lotteryActor = system.actorOf(LotteryActor.props, "actor-lottery")

  lotteryActor ! StartLottery(3)
}
