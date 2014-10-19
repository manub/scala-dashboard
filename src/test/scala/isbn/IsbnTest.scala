package isbn

import org.scalatest.{FlatSpec, Matchers}

class IsbnTest extends FlatSpec with Matchers {


  "An isbn" should "be valid for a ISBN13" in {
    Isbn("9788866325154").isValid should equal (true)
  }

  it should "be not valid for a non valid 13 digits sequence" in {
    Isbn("1234567890123").isValid should equal (false)
  }

  it should "be not valid for an ISBN neither with 10 nor 13 digits" in {
    Isbn("123456789").isValid should equal (false)
  }

  it should "be valid for a valid ISBN10" in {
    Isbn("0306406152").isValid should equal (true)
  }

  it should "be valid for a valid ISBN10 with dashes" in {
    Isbn("0-306-40615-2").isValid should equal (true)
  }

}
