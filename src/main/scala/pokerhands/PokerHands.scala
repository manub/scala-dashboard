package pokerhands

object PokerHands {

  case class Winner(name: String, hand: String)

  val cards = Map(
    "2" -> "Two",
    "3" -> "Three",
    "4" -> "Four",
    "5" -> "Five",
    "6" -> "Six",
    "7" -> "Seven",
    "8" -> "Eight",
    "9" -> "Nine",
    "J" -> "Jack",
    "Q" -> "Queen",
    "K" -> "King",
    "A" -> "Ace"
  )

  def winner(hands: String): String = {

    object CardOrdering extends Ordering[String] {
      val order = List('2', '3', '4', '5', '6', '7', '8', '9', 'J', 'Q', 'K', 'A')
      def compare(x: String, y: String): Int = {
        if (x == y) 0
        else order.indexOf(x(0)) - order.indexOf(y(0))
      }
    }

    def winningPlayer(blackCards: List[String], whiteCards: List[String]) = {
      if (containsAce(blackCards)) Winner("Black", "with high card: Ace")
      else if (containsAce(whiteCards)) Winner("White", "with high card: Ace")
      else {
        val winner = highCard(blackCards, whiteCards)
        Winner(winner._1, s"with high card: ${cards(winner._2.substring(0, 1))}")
      }
    }

    def highCard(blackCards: List[String], whiteCards: List[String]): (String, String) = {
      val black = ("Black", blackCards.max(CardOrdering))
      val white = ("White", whiteCards.max(CardOrdering))
      CardOrdering.compare(black._2, white._2) match {
        case 1 => black
        case -1 => white
        case default => throw new RuntimeException
      }
    }

    def containsAce(cards: List[String]): Boolean = {
      val aceRegex = "^A.?$".r
      cards.exists(_ match {
        case aceRegex() => true
        case default => false
      })
    }

    def parseHands(input: String): (String, String) = {
      val regex = "^Black: (.*)  White: (.*)".r
      input match {
        case regex(black, white) => (black, white)
      }
    }

    def parseCards(hand: String): List[String] = {
      hand.split(" ").toList
    }

    def isTie(blackCards: List[String], whiteCards: List[String]): Boolean = {
      whiteCards.map { toFirstCharacter } == blackCards.map { toFirstCharacter }
    }

    def toFirstCharacter: String => Character = {
      string: String => string(0)
    }

    val (blackHand, whiteHand) = parseHands(hands)
    val blackCards = parseCards(blackHand)
    val whiteCards = parseCards(whiteHand)

    if (isTie(blackCards, whiteCards)) "Tie."
    else {
      val winner = winningPlayer(blackCards, whiteCards)
      s"${winner.name} wins. - ${winner.hand}"
    }
  }
}
