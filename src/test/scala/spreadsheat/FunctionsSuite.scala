package spreadsheat

import spreadsheat.Cell.isCellNum
import spreadsheat.Cell.isCellEmpty

//import spreadsheat.Functions.sum


class FunctionsSuite extends munit.FunSuite {
  test("sum that works") {
    //sum
    assertEquals(sum(List(Cell.Number(1), Cell.Empty, Cell.Number(3), Cell.Number(4))), Cell.Number(8))
  }
  test("sum with text") {
    //sum
    assertEquals(sum(List(Cell.Number(1), Cell.Empty, Cell.Text("3"), Cell.Number(4))), Cell.ErrorCell)
  }

  test("addition number + text") {
    //addition
    assertEquals(addition(Cell.Number(2), Cell.Text("ed")), Cell.ErrorCell)
  }
  test("addition that works") {
    //addition
    assertEquals(addition(Cell.Number(2), Cell.Number(5)), Cell.Number(7))
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