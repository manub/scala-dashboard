object Main extends App {


  override def main(args: Array[String]) {
    val board = args(0)
    val symbol: Char = if (board.count(_ == '-') % 2 == 0) 'x' else '0'
    val horizontalWin = checkHorizontal(board, symbol)
    val verticalWin = checkVertical(board, symbol)
    val move = if (horizontalWin != -1) horizontalWin else board indexOf '-'
    println(move)
  }

  def checkHorizontal(board: String, symbol: Char): Int = {
    if (canWinAtRow(board, symbol, 0)) board.substring(0, 3).indexOf('-')
    else if (canWinAtRow(board, symbol, 1)) board.substring(3, 6).indexOf('-') + 3
    else if (canWinAtRow(board, symbol, 2)) board.substring(6, 9).indexOf('-') + 6
    else -1
  }

  def generateColumn(board: String, column: Int) =
    board(column).toString + board(column + 3).toString + board(column + 6).toString

  def canWinAtColumn(board: String, symbol: Char, column: Int): Boolean = {
    val boardPortion: String = board(column).toString + board(column + 3).toString + board(column + 6).toString

    if (boardPortion.count(_ == symbol) == 2) {
      val tentativeIndex = boardPortion.indexOf(boardPortion.filter(_ != symbol))
      if (boardPortion(tentativeIndex) == '-') true else false
    } else false
  }

  def checkVertical(board: String, symbol: Char) = {
    if (canWinAtColumn(board, symbol, 0)) generateColumn(board, 0).indexOf('-')
    else if (canWinAtColumn(board, symbol, 1)) board.substring(3, 6).indexOf('-') + 3
    else if (canWinAtColumn(board, symbol, 2)) board.substring(6, 9).indexOf('-') + 6
    else -1
  }


  def canWinAtRow(board: String, symbol: Char, row: Int) = {
    val startingIndex = 3 * row
    val boardPortion = board.substring(startingIndex, startingIndex + 3)

    if (boardPortion.count(_ == symbol) == 2) {
      val tentativeIndex = boardPortion.indexOf(boardPortion.filter(_ != symbol))
      if (boardPortion(tentativeIndex) == '-') true else false
    } else false

  }

}
