import ElectorActor.Winners
import LotteryActor.ElectWinners
import akka.actor.SupervisorStrategy.{Restart, Resume}
import akka.actor.{Actor, OneForOneStrategy, Props, SupervisorStrategy}

import scala.util.Random

class ElectorActor extends Actor {

  def Eliminator(participants: List[String], numberWinners: Int): List[String] = {
    Random.shuffle(participants)
      .take(3)
  }

  override def receive: Receive = {
    case ElectWinners(participants, numberWinners) =>
      val windners = Eliminator(participants, numberWinners)
      sender() ! Winners(winners)
  }

//  override def supervisorStrategy: SupervisorStrategy = {
//    OneForOneStrategy(){
//      case IllegalArgumentException => Resume
//      case Exception => Restart
//    }
//  }
}

object ElectorActor {
  def props = Props[ElectorActor]
  case class Winners(winners: List[String])
}
