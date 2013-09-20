package pokerhands

import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers

class PokerHandsSpec extends FunSpec with ShouldMatchers {

  describe("Two poker hands") {
    describe ("when the same") {
      it("should result in a tie") {
        PokerHands.winner("Black: 2H 3D 5S 9C KD  White: 2D 3H 5C 9S KH") should be ("Tie.")
      }
    }
    describe ("when a player wins by high card") {
      it("should announce the correct winning hand and player") {
        PokerHands.winner("Black: 2H 3D 5S 9C KD  White: 2D 3H 5C 9S AH") should be ("White wins. - with high card: Ace")
        PokerHands.winner("Black: 2H 3D 5S 9C AD  White: 2D 3H 5C 9S KH") should be ("Black wins. - with high card: Ace")
      }
      it("should announce the correct winning card") {
        PokerHands.winner("Black: 2H 3D 5S 9C KD  White: 2D 3H 5C 9S QH") should be ("Black wins. - with high card: King")
      }
    }
  }

}
