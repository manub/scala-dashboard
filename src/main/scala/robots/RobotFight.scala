import scala.util.Random

object RobotFight extends App {

    val move = new Random nextInt 3 match {
      case 0 => "rock"
      case 1 => "paper"
      case 2 => "scissors"
    }
    println(move)

}
