package pokerhands

object PokerHands {

  def winner(hands: String): String = {

    def winningPlayer(blackCards: List[String], whiteCards: List[String]) = {
      if (containsAce(blackCards)) "Black" else "White"
    }

    def containsAce(cards: List[String]): Boolean = {
      val aceRegex = "^A.?$".r
      cards.exists(_ match {
        case aceRegex() => true
        case default => false
      })
    }

    def parseHands(input: String): (String, String)  = {
      val regex = "^Black: (.*)  White: (.*)".r
      input match {
        case regex(black, white) =>  (black, white)
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

    if (isTie(blackCards, whiteCards))  "Tie."
    else s"${winningPlayer(blackCards, whiteCards)} wins. - with high card: Ace"

  }

}
