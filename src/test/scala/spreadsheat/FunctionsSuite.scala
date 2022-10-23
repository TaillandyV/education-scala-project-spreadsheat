package spreadsheat

import spreadsheat.Cell.isCellNum
import spreadsheat.Cell.isCellEmpty

//import spreadsheat.Functions.sum


class FunctionsSuite extends munit.FunSuite {
  test("sum") {
    //sum
    var l = List(Cell.Number(1), Cell.Empty, Cell.Text("3"), Cell.Number(4))
    var res = sum(l)
    println(res)
    //assertEquals(res, 10)
  }


  test("addition") {
    //addition
    var l = Cell.Number(2)
    var l2 = Cell.Text("ed")
    var res = addition(l, l2)
    println(res)
    //assertEquals(res, 10)
  }
  /*
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
    }*/
}