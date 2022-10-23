package spreadsheat
import munit.Clue.generate
import spreadsheat.Cell

import math.Ordered.orderingToOrdered
import math.Ordering.Implicits.infixOrderingOps
import munit.Clue.generate
import spreadsheat.Cell.{isCellEmpty, isCellNum, stringToBooleen}

class Functions2Suite extends munit.FunSuite {

  test("Equal") {
    val s1 = Cell.Number(124)
    val s2 = Cell.Number(124)
    val result = s1.equals(s2)
    println(Cell.Booleen(result))
    assertEquals(result, true)
  }


  test("inf that works") {
    //inf

    assertEquals(inf(Cell.Number(1), Cell.Number(2)), Cell.Booleen(true))
  }

  test("inf number + text") {
    //inf
    assertEquals(inf(Cell.Number(2), Cell.Text("ed")), Cell.ErrorCell)
  }

  test("supp that works") {
    //supp

    assertEquals(supp(Cell.Number(3), Cell.Number(2)), Cell.Booleen(true))
  }

  test("supp number + text") {
    //inf
    assertEquals(supp(Cell.Number(2), Cell.Text("ed")), Cell.ErrorCell)
  }

  test("infOrEqual that works") {
    //inf

    assertEquals(infOrEqual(Cell.Number(2), Cell.Number(2)), Cell.Booleen(true))
  }

  test("infOrEqual number + text") {
    //inf
    assertEquals(infOrEqual(Cell.Number(2), Cell.Text("ed")), Cell.ErrorCell)
  }

  test("supOrEqual that works") {
    //inf

    assertEquals(supOrEqual(Cell.Number(2), Cell.Number(2)), Cell.Booleen(true))
  }

  test("supOrEqual number + text") {
    //inf
    assertEquals(supOrEqual(Cell.Number(2), Cell.Text("ed")), Cell.ErrorCell)
  }
}


