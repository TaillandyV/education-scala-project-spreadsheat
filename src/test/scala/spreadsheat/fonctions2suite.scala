package spreadsheat
import munit.Clue.generate
import spreadsheat.Cell

import math.Ordered.orderingToOrdered
import math.Ordering.Implicits.infixOrderingOps
import munit.Clue.generate
import spreadsheat.Cell.{isCellEmpty, isCellNum, stringToBooleen}

def equals(l1 : Cell, l2: Cell): Boolean = l1.equals(l2)

def inf(cell1: Cell, cell2: Cell): Cell =
  if (isCellNum(cell1) && isCellNum(cell2)) {
    val a1 = cell1.getNum.get
    val a2 = cell2.getNum.get
    Cell.Booleen(a1 < a2)
  }
  else if (isCellEmpty(cell1)) {
    Cell.Number(cell2.getNum.get)
  }
  else if (isCellEmpty(cell2)) {
    Cell.Number(cell1.getNum.get)
  }
  else Cell.ErrorCell

def supp(cell1: Cell, cell2: Cell): Cell =
  if (isCellNum(cell1) && isCellNum(cell2)) {
    val a1 = cell1.getNum.get
    val a2 = cell2.getNum.get
    Cell.Booleen(a1 > a2)
  }
  else if (isCellEmpty(cell1)) {
    Cell.Number(cell2.getNum.get)
  }
  else if (isCellEmpty(cell2)) {
    Cell.Number(cell1.getNum.get)
  }
  else Cell.ErrorCell

def infouegale(cell1: Cell, cell2: Cell): Cell =
  if (isCellNum(cell1) && isCellNum(cell2)) {
    val a1 = cell1.getNum.get
    val a2 = cell2.getNum.get
    Cell.Booleen(a1 <= a2)
  }
  else if (isCellEmpty(cell1)) {
    Cell.Number(cell2.getNum.get)
  }
  else if (isCellEmpty(cell2)) {
    Cell.Number(cell1.getNum.get)
  }
  else Cell.ErrorCell

def suppouegale(cell1: Cell, cell2: Cell): Cell =
  if (isCellNum(cell1) && isCellNum(cell2)) {
    val a1 = cell1.getNum.get
    val a2 = cell2.getNum.get
    Cell.Booleen(a1 >= a2)
  }
  else if (isCellEmpty(cell1)) {
    Cell.Number(cell2.getNum.get)
  }
  else if (isCellEmpty(cell2)) {
    Cell.Number(cell1.getNum.get)
  }
  else Cell.ErrorCell

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

  test("infouegale that works") {
    //inf

    assertEquals(infouegale(Cell.Number(2), Cell.Number(2)), Cell.Booleen(true))
  }

  test("infouegale number + text") {
    //inf
    assertEquals(infouegale(Cell.Number(2), Cell.Text("ed")), Cell.ErrorCell)
  }

  test("suppouegale that works") {
    //inf

    assertEquals(suppouegale(Cell.Number(2), Cell.Number(2)), Cell.Booleen(true))
  }

  test("suppouegale number + text") {
    //inf
    assertEquals(suppouegale(Cell.Number(2), Cell.Text("ed")), Cell.ErrorCell)
  }
}


