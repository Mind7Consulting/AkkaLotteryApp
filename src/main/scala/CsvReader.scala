import CsvReader.{ListParticipants, ReadParticipants}
import akka.actor.{Actor, Props}

import scala.io.Source

class CsvReader extends Actor {
  override def receive: Receive = {
    case ReadParticipants(filename) =>
      val participants: Iterator[String] = Source.fromResource(filename).getLines
      sender() ! ListParticipants(participants.toList)
  }
}

object CsvReader {
  def props = Props[CsvReader]

  case class ReadParticipants(filename: String)
  case class ListParticipants(list: List[String])


}
