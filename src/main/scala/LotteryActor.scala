import CsvReader.{ListParticipants, ReadParticipants}
import ElectorActor.Winners
import LotteryActor.{ElectWinners, StartLottery}
import akka.actor.{Actor, Props}

class LotteryActor extends Actor {
  val readerRef = context.actorOf(CsvReader.props, "reader")
  val electorActorRef = context.actorOf(ElectorActor.props, "elector-actor")
//  var numberWinners : Int = 0



  //  override def receive: Receive = {
//    case StartLottery(numWinners) =>
//      numberWinners = numWinners
//      readerRef ! ReadParticipants("participants.csv")
//    case ListParticipants(listParticipants) =>
//      electorActorRef ! ElectWinners(listParticipants, numberWinners)
//    case Winners(listWinners) =>
//      listWinners.foreach( winner => {
//        println(s"\nThe winner is ...")
//        Thread.sleep(3000)
//        println(winner)
//        println("\n congrats ! \n")
//      })
//  }
  override def receive: Receive = {
    case StartLottery(numWinners) =>
//      numberWinners = numWinners
      readerRef ! ReadParticipants("participants.csv")
      context.become(electionBehaviour(numWinners))
  }
  def electionBehaviour(numWinners: Int): Receive = {
    case ListParticipants(listParticipants) =>
      electorActorRef ! ElectWinners(listParticipants, numWinners)
    case Winners(listWinners) =>
      listWinners.foreach( winner => {
        println(s"\nThe winner is ...")
        Thread.sleep(3000)
        println(winner)
        println("congrats !")
      })
  }
}

object LotteryActor {
  def props = Props[LotteryActor]

  case class StartLottery(numberWinners: Int)

  case class ElectWinners(participants: List[String], numberWinners: Int)

}
