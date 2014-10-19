package lsccoctober

case class Order(items: Map[String, Int] = Map.empty) {

  def value: Double = calculatePrice

  def calculatePrice: Double = {
    val numberOfDifferentBooks = items.size
    val totalNumberOfBooksOrdered = items.values.sum

    val fullPricePercentage = numberOfDifferentBooks match {
      case 0 => 1
      case 1 => 1
      case 2 => 0.95
      case 3 => 0.9
      case 4 => 0.8
      case 5 => 0.75
    }

    val discountedPriceForDifferentBooks = numberOfDifferentBooks * 8 * fullPricePercentage

    val fullPriceForNonDifferentBooks = (totalNumberOfBooksOrdered - numberOfDifferentBooks) * 8

    discountedPriceForDifferentBooks + fullPriceForNonDifferentBooks
  }


}
