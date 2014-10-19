package isbn

case class Isbn(value: String) {

  val isbn = value.filter { _.isDigit }

  def isEven(value: Int): Boolean = value % 2 == 0

  def addCheckSum(sum: Int, elem: (Int, Int)) = {
    val (digit, index) = elem
    sum + (digit * (if (isEven(index)) 3 else 1))
  }

  def indexTo1Based(elem: (Char, Int)): (Int, Int) = {
    val (digit, index) = elem
    (digit.asDigit, index + 1)
  }

  def isValid: Boolean = isbn.length match {
    case 13 =>
      isbn.zipWithIndex
        .map(indexTo1Based)
        .foldLeft(0)(addCheckSum) % 10 == 0

    case 10 =>
      val controlDigit = if (isbn.last == 'X') 10 else isbn.last.asDigit
      val finalSum = isbn.zipWithIndex
        .dropRight(1)
        .map(indexTo1Based)
        .foldLeft(0) { case (sum, (digit, index)) => sum + digit * (11 - index) }

      11 - (finalSum % 11) == controlDigit

    case _ =>
      false
  }
}