def mapFun[T, U](xs: List[T], f: T => U): List[U] =
  (xs foldRight List[U]())((x, xs) => f(x) :: xs)

def f(x: Int): Int = x * x

mapFun(List(1, 2, 3), f)

def lengthFun[T](xs: List[T]): Int =
  (xs foldRight 0)((_, x) => x + 1)

lengthFun(List(1,2,3))