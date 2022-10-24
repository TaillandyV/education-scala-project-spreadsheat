package spreadsheat

import spreadsheat.Cell.isCellNum
import spreadsheat.Cell.isCellEmpty

//import spreadsheat.Functions.sum


class FunctionsSuite extends munit.FunSuite {

  test("inf that works"){
    val cell1 = Cell.Number(1)
    val cell2 = Cell.Number(2)
    assertEquals(compareOperator(cell1,"<", cell2), Cell.Booleen(true))
    assertEquals(compareOperator(cell2,"<", cell1), Cell.Booleen(false))
    assertEquals(compareOperator(cell1,"<", cell1), Cell.Booleen(false))
  }

  test("inf number + text") {
    val cell1 = Cell.Number(1)
    val cell2 = Cell.Text("oui")
    assertEquals(compareOperator(cell1,"<", cell2), Cell.ErrorCell)
    assertEquals(compareOperator(cell2,"<", cell1), Cell.ErrorCell)
    assertEquals(compareOperator(cell2,"<", cell2), Cell.ErrorCell)
  }

  test("supp that works") {
    val cell1 = Cell.Number(3)
    val cell2 = Cell.Number(2)

    assertEquals(compareOperator(cell1, ">", cell2), Cell.Booleen(true))
    assertEquals(compareOperator(cell2, ">", cell1), Cell.Booleen(false))
    assertEquals(compareOperator(cell1, ">", cell1), Cell.Booleen(false))
  }

  test("supp number + text") {
    val cell1 = Cell.Number(1)
    val cell2 = Cell.Text("oui")
    assertEquals(compareOperator(cell1, ">",cell2), Cell.ErrorCell)
    assertEquals(compareOperator(cell2, ">",cell1), Cell.ErrorCell)
    assertEquals(compareOperator(cell2,"<", cell2), Cell.ErrorCell)
  }

  test("infOrEqual that works") {
    val cell1 = Cell.Number(1)
    val cell2 = Cell.Number(2)

    assertEquals(compareOperator(cell2, "<=",cell2), Cell.Booleen(true))
    assertEquals(compareOperator(cell1, "<=",cell2), Cell.Booleen(true))
    assertEquals(compareOperator(cell2, "<=",cell1), Cell.Booleen(false))
  }

  test("infOrEqual number + text") {
    val cell1 = Cell.Number(1)
    val cell2 = Cell.Text("oui")

    assertEquals(compareOperator(cell1, "<=",cell2), Cell.ErrorCell)
    assertEquals(compareOperator(cell2, "<=",cell1), Cell.ErrorCell)
    assertEquals(compareOperator(cell2,"<", cell2), Cell.ErrorCell)
  }

  test("supOrEqual that works") {
    val cell1 = Cell.Number(1)
    val cell2 = Cell.Number(2)

    assertEquals(compareOperator(cell1, ">=",cell2), Cell.Booleen(false))
    assertEquals(compareOperator(cell2, ">=",cell1), Cell.Booleen(true))
    assertEquals(compareOperator(cell2, ">=",cell2), Cell.Booleen(true))
  }

  test("supOrEqual number + text") {
    val cell1 = Cell.Number(1)
    val cell2 = Cell.Text("oui")

    assertEquals(compareOperator(cell1, ">=",cell2), Cell.ErrorCell)
    assertEquals(compareOperator(cell2, ">=",cell1), Cell.ErrorCell)
    assertEquals(compareOperator(cell2,"<", cell2), Cell.ErrorCell)
  }

  test("sum that works") {
    val liste = List(Cell.Number(1), Cell.Empty, Cell.Number(3), Cell.Number(4))
    assertEquals(sum(liste), Cell.Number(8))
  }
  test("sum with text") {
    //sum
    assertEquals(sum(List(Cell.Number(1), Cell.Empty, Cell.Text("3"), Cell.Number(4))), Cell.ErrorCell)
  }

  test("addition number + text") {
    //addition
    assertEquals(addition(Cell.Number(2), Cell.Text("ed")), Cell.ErrorCell)
    println(addition(Cell.Number(2), Cell.Text("ed")))
  }
  test("addition cell empty + text") {
    //addition
    assertEquals(addition(Cell.Number(2), Cell.Text("ed")), Cell.ErrorCell)
    println(addition(Cell.Empty, Cell.Text("ed")))
  }
  test("addition that works") {
    //addition
    assertEquals(addition(Cell.Number(2), Cell.Number(5)), Cell.Number(7))
    println(addition(Cell.Number(2), Cell.Number(5)))
  }
  test("soustraction number + text") {
    //soustraction
    assertEquals(minus(Cell.Number(2), Cell.Text("ed")), Cell.ErrorCell)
    println(minus(Cell.Number(2), Cell.Text("ed")))
  }
  test("soustraction cell empty + text") {
    //soustraction
    assertEquals(minus(Cell.Number(2), Cell.Text("ed")), Cell.ErrorCell)
    println(minus(Cell.Empty, Cell.Text("ed")))
  }
  test("soustraction that works") {
    //soustraction
    assertEquals(minus(Cell.Number(-2), Cell.Number(-5)), Cell.Number(3))
    println(minus(Cell.Number(-2), Cell.Number(-5)))
  }

  test("multiplication number + text") {
    //multiplication
    assertEquals(multiplication(Cell.Number(2), Cell.Text("ed")), Cell.ErrorCell)
    println(multiplication(Cell.Number(2), Cell.Text("ed")))
  }
  test("multiplication cell empty + text") {
    //multiplication
    assertEquals(multiplication(Cell.Number(2), Cell.Text("ed")), Cell.ErrorCell)
    println(multiplication(Cell.Empty, Cell.Text("ed")))
  }
  test("multiplication that works") {
    //multiplication
    assertEquals(multiplication(Cell.Number(-2), Cell.Number(1)), Cell.Number(-2))
    println(multiplication(Cell.Number(-2), Cell.Number(1)))
  }

  test("division number + text") {
    //division
    assertEquals(division(Cell.Number(2), Cell.Text("ed")), Cell.ErrorCell)
    println(division(Cell.Number(2), Cell.Text("ed")))
  }
  test("division cell empty + text") {
    //division
    assertEquals(division(Cell.Number(2), Cell.Text("ed")), Cell.ErrorCell)
    println(division(Cell.Empty, Cell.Text("ed")))
  }
  test("division number/0") {
    //division
    assertEquals(division(Cell.Number(0), Cell.Number(0)), Cell.ErrorCell)
    println(division(Cell.Number(0), Cell.Number(0)))
  }
  test("division that works") {
    //division
    assertEquals(division(Cell.Number(-0), Cell.Number(0)), Cell.Number(0))
    println(division(Cell.Number(-0), Cell.Number(0)))
  }

  test("concat text + number") {
    //concat
    assertEquals(concat(Cell.Number(2), Cell.Text("ed")), Cell.ErrorCell)
    println(concat(Cell.Number(2), Cell.Empty))
  }
  test("concat cell empty + number") {
    //division
    assertEquals(concat(Cell.Number(2), Cell.Text("ed")), Cell.ErrorCell)
    println(concat(Cell.Empty, Cell.Number(2)))
  }
  test("concat that works") {
    //concat
    assertEquals(concat(Cell.Text("hello"), Cell.Text("world")), Cell.Text("hello world"))
    println(concat(Cell.Empty, Cell.Text("world")))
  }

  test("minimum with text") {
    //minimum
    assertEquals(minimum(List(Cell.Number(1), Cell.Empty, Cell.Text("3"), Cell.Number(4))), Cell.ErrorCell)
    println(minimum(List(Cell.Number(1), Cell.Number(2), Cell.Text("3"), Cell.Number(4))))
  }
  test("minimum that works") {
    //minimum
    assertEquals(minimum(List(Cell.Number(1), Cell.Number(2), Cell.Number(3), Cell.Number(4))), Cell.Number(1))
    println(minimum(List(Cell.Number(1), Cell.Number(2), Cell.Number(3), Cell.Number(4))))
  }

  test("maximum with text") {
    //maximum
    assertEquals(maximum(List(Cell.Number(1), Cell.Empty, Cell.Text("3"), Cell.Number(4))), Cell.ErrorCell)
    println(maximum(List(Cell.Number(1), Cell.Number(2), Cell.Text("3"), Cell.Number(4))))
  }
  test("maximum that works") {
    //maximum
    assertEquals(maximum(List(Cell.Number(1), Cell.Number(2), Cell.Number(3), Cell.Number(4))), Cell.Number(4))
    println(maximum(List(Cell.Number(1), Cell.Number(2), Cell.Number(3), Cell.Number(4))))
  }
 
}