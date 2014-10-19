class Rational(x: Int, y: Int) {
  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)

  def g = gcd(x, y)

  require(y != 0, "denominator must not be 0")

  def numer = x
  def denom = y

  def add(that: Rational) =
    new Rational(numer * that.denom + that.numer * denom,
      denom * that.denom)
  def neg() = new Rational(-numer, denom)
  def sub(that: Rational) = add(that.neg())
  def less(that: Rational) = numer * that.denom < that.numer * denom
  def max(that: Rational) = if (less(that)) that else this
  override def toString() = numer/g + "/" + denom/g
}
val x = new Rational(1, 3)
val y = new Rational(5, 7)
val z = new Rational(3, 2)
x.sub(y).sub(z)
y.add(y)
x.less(y)
x.max(y)