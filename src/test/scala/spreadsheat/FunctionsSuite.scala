package spreadsheat

import spreadsheat.Cell.isCellNum
import spreadsheat.Cell.isCellEmpty

//import spreadsheat.Functions.sum


class FunctionsSuite extends munit.FunSuite {

  //-------------------------------Operator "<"----------------------------------\\
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

  //-------------------------------Operator ">"----------------------------------\\
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

  //-------------------------------Operator "<="----------------------------------\\
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

  //-------------------------------Operator ">="----------------------------------\\
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

  //-------------------------------Function "sum"----------------------------------\\
  test("sum that works") {
    val listOfCells = List(Cell.Number(1), Cell.Number(2), Cell.Number(3), Cell.Number(4))

    assertEquals(sum(listOfCells), Cell.Number(10))
  }

  test("sum with empty") {
    val listOfCells = List(Cell.Number(1), Cell.Empty, Cell.Number(3), Cell.Number(4))

    assertEquals(sum(listOfCells), Cell.Number(8))
  }

  test("sum with text") {
    val listOfCells = List(Cell.Number(1), Cell.Empty, Cell.Text("3"), Cell.Number(4))

    assertEquals(sum(listOfCells), Cell.ErrorCell)
  }

  //-------------------------------Operator "+"----------------------------------\\
  test("addition that works") {
    val cell1 = Cell.Number(1)
    val cell2 = Cell.Number(2)

    assertEquals(calculateOperator(cell1, "+", cell2), Cell.Number(3))
    assertEquals(calculateOperator(cell2, "+", cell1), Cell.Number(3))
  }

  test("addition number + empty") {
    val cell1 = Cell.Number(1)
    val cell2 = Cell.Empty

    assertEquals(calculateOperator(cell1, "+", cell2), Cell.Number(1))
    assertEquals(calculateOperator(cell2, "+", cell1), Cell.Number(1))
  }

  test("addition number + text") {
    val cell1 = Cell.Number(1)
    val cell2 = Cell.Text("2")

    assertEquals(calculateOperator(cell1, "+", cell2), Cell.ErrorCell)
    assertEquals(calculateOperator(cell2, "+", cell1), Cell.ErrorCell)
  }

  test("addition cell empty + text") {
    val cell1 = Cell.Empty
    val cell2 = Cell.Text("2")

    assertEquals(calculateOperator(cell1, "+",cell2), Cell.ErrorCell)
    assertEquals(calculateOperator(cell2, "+",cell1), Cell.ErrorCell)
  }

  //-------------------------------Operator "-"----------------------------------\\
  test("soustraction that works") {
    val cell1 = Cell.Number(1)
    val cell2 = Cell.Number(2)

    assertEquals(calculateOperator(cell1, "-", cell2), Cell.Number(-1))
    assertEquals(calculateOperator(cell2, "-", cell1), Cell.Number(1))
  }

  test("soustraction number + empty") {
    val cell1 = Cell.Number(1)
    val cell2 = Cell.Empty

    assertEquals(calculateOperator(cell1, "-", cell2), Cell.Number(1))
    assertEquals(calculateOperator(cell2, "-", cell1), Cell.Number(-1))
  }

  test("soustraction number + text") {
    val cell1 = Cell.Number(1)
    val cell2 = Cell.Text("2")

    assertEquals(calculateOperator(cell1, "-", cell2), Cell.ErrorCell)
    assertEquals(calculateOperator(cell2, "-", cell1), Cell.ErrorCell)
  }
  test("soustraction cell empty + text") {
    val cell1 = Cell.Empty
    val cell2 = Cell.Text("2")

    assertEquals(calculateOperator(cell1, "-", cell2), Cell.ErrorCell)
    assertEquals(calculateOperator(cell2, "-", cell1), Cell.ErrorCell)
  }

  //-------------------------------Operator "*"----------------------------------\\
  test("multiplication that works") {
    val cell1 = Cell.Number(3)
    val cell2 = Cell.Number(4)

    assertEquals(calculateOperator(cell1, "*", cell2), Cell.Number(12))
    assertEquals(calculateOperator(cell2, "*", cell1), Cell.Number(12))
  }

  test("multiplication number + empty") {
    val cell1 = Cell.Number(3)
    val cell2 = Cell.Empty

    assertEquals(calculateOperator(cell1, "*", cell2), Cell.Number(0))
    assertEquals(calculateOperator(cell2, "*", cell1), Cell.Number(0))
  }

  test("multiplication number + text") {
    val cell1 = Cell.Number(3)
    val cell2 = Cell.Text("4")

    assertEquals(calculateOperator(cell1, "*", cell2), Cell.ErrorCell)
    assertEquals(calculateOperator(cell2, "*", cell1), Cell.ErrorCell)
  }

  test("multiplication cell empty + text") {
    val cell1 = Cell.Empty
    val cell2 = Cell.Text("4")

    assertEquals(calculateOperator(cell1, "*", cell2), Cell.ErrorCell)
    assertEquals(calculateOperator(cell2, "*", cell1), Cell.ErrorCell)
  }

  //-------------------------------Operator "/"----------------------------------\\
  test("division that works") {
    val cell1 = Cell.Number(4)
    val cell2 = Cell.Number(2)

    assertEquals(calculateOperator(cell1, "/", cell2), Cell.Number(2))
    assertEquals(calculateOperator(cell2, "/", cell1), Cell.Number(0.5))
  }

  test("division number + text") {
    val cell1 = Cell.Number(4)
    val cell2 = Cell.Text("2")

    assertEquals(calculateOperator(cell1, "/", cell2), Cell.ErrorCell)
    assertEquals(calculateOperator(cell2, "/", cell1), Cell.ErrorCell)
  }

  test("division empty + text") {
    val cell1 = Cell.Empty
    val cell2 = Cell.Text("2")

    assertEquals(calculateOperator(cell1, "/", cell2), Cell.ErrorCell)
    assertEquals(calculateOperator(cell2, "/", cell1), Cell.ErrorCell)
  }

  test("division number + 0") {
    val cell1 = Cell.Number(4)
    val cell2 = Cell.Number(0)

    assertEquals(calculateOperator(cell1, "/", cell2), Cell.ErrorCell)
    assertEquals(calculateOperator(cell2, "/", cell1), Cell.Number(0))
  }
  test("division number + empty") {
    val cell1 = Cell.Number(4)
    val cell2 = Cell.Empty

    assertEquals(calculateOperator(cell1, "/", cell2), Cell.ErrorCell)
    assertEquals(calculateOperator(cell2, "/", cell1), Cell.Number(0))
  }

  //-------------------------------Function "concat"----------------------------------\\
  test("concat text + text") {
    val cell1 = Cell.Text("Hello")
    val cell2 = Cell.Text("World")

    assertEquals(concat(cell1, cell2), Cell.Text("HelloWorld"))
    assertEquals(concat(cell2, cell1), Cell.Text("WorldHello"))
  }

  test("concat text + number") {
    val cell1 = Cell.Text("Hello")
    val cell2 = Cell.Number(1)

    assertEquals(concat(cell1, cell2), Cell.ErrorCell)
    assertEquals(concat(cell2, cell1), Cell.ErrorCell)
  }

  test("concat empty + number") {
    val cell1 = Cell.Empty
    val cell2 = Cell.Number(1)

    assertEquals(concat(cell1, cell2), Cell.Number(1))
    assertEquals(concat(cell2, cell1), Cell.Number(1))
  }

  test("concat empty + text") {
    val cell1 = Cell.Empty
    val cell2 = Cell.Text("World")

    assertEquals(concat(cell1, cell2), Cell.Text("World"))
    assertEquals(concat(cell2, cell1), Cell.Text("World"))
  }

  test("concat number + number") {
    val cell1 = Cell.Number(1)
    val cell2 = Cell.Number(2)

    assertEquals(concat(cell1, cell2), Cell.Number(12))
    assertEquals(concat(cell2, cell1), Cell.Number(21))
  }

  //-------------------------------Function "minimum"----------------------------------\\
  test("minimum with only numbers") {
    val cell1 = Cell.Number(3)
    val cell2 = Cell.Number(2)
    val cell3 = Cell.Number(1)
    val cell4 = Cell.Number(4)

    val listOfCells = List(cell1, cell2, cell3, cell4)
    assertEquals(min(listOfCells), Cell.Number(1))
  }
  test("minimum with text") {
    val cell1 = Cell.Number(3)
    val cell2 = Cell.Text("2")
    val cell3 = Cell.Number(1)
    val cell4 = Cell.Number(4)

    val listOfCells = List(cell1, cell2, cell3, cell4)
    assertEquals(min(listOfCells), Cell.ErrorCell)
  }
  test("minimum with empty") {
    val cell1 = Cell.Number(3)
    val cell2 = Cell.Empty
    val cell3 = Cell.Number(2)
    val cell4 = Cell.Number(4)

    val listOfCells = List(cell1, cell2, cell3, cell4)
    assertEquals(min(listOfCells), Cell.Number(2))
  }


  test("maximum with text") {
    //maximum
    assertEquals(max(List(Cell.Number(1), Cell.Empty, Cell.Text("3"), Cell.Number(4))), Cell.ErrorCell)
    println(max(List(Cell.Number(1), Cell.Number(2), Cell.Text("3"), Cell.Number(4))))
  }
  test("maximum that works") {
    //maximum
    assertEquals(max(List(Cell.Number(1), Cell.Number(2), Cell.Number(3), Cell.Number(4))), Cell.Number(4))
    println(max(List(Cell.Number(1), Cell.Number(2), Cell.Number(3), Cell.Number(4))))
  }

}
