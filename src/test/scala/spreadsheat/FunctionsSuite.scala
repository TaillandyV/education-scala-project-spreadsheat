package spreadsheat

//import spreadsheat.Functions

def sum(l: List[Int]): Int = l.head + l.tail.foldLeft(0)(_ + _)

def moins(l: List[Int]): Int = l.head + l.tail.foldLeft(0)(_ - _)

def multiplier(l: List[Int]): Int = l.head * l.tail.foldLeft(1)(_ * _)

def diviser(l: List[Double]): Double = l.head * l.tail.foldLeft(1.0)((acc, curr) => acc / curr)

def concat(v1: String, v2: String): String = v1 + v2

def minimum(mini: List[Int]): Int =
  {
    mini.min
  }

def maximum(maxi: List[Int]): Int =
{
  maxi.max
}

class FunctionsSuite extends munit.FunSuite {
  test("addition") {
    //addition
    var l = List(1, 2, 3, 4)
    var res = sum(l)
    assertEquals(res, 10)
  }

  test("soustraction") {
    //soustraction
    var l = List(1, 2, 3, 4)
    var res = moins(l)
    assertEquals(res, -8)
  }


  test("multiplication") {
    //multiplication
    var l = List(2, 4)
    var res = multiplier(l)
    assertEquals(res, 8)
  }

  test("division") {
    //division
    var l = List(16.0, 4.0)
    var res = diviser(l)
    assertEquals(res, 4.0)
  }

  test("minimum") {
    // Minimum
    var mini = List(1, 2, 3, 4)
    var resmin = minimum(mini)
    assertEquals(resmin, 1)
  }

  test("Maximum") {
    // Maximum
    var maxi = List(1, 2, 3, 4)
    var resmax = maximum(maxi)
    assertEquals(resmax, 4)
  }
}
