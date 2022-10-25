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

  test("minimum with all empty") {
    val cell1 = Cell.Empty
    val cell2 = Cell.Empty
    val cell3 = Cell.Empty
    val cell4 = Cell.Empty
    val listOfCells = List(cell1, cell2, cell3, cell4)

    assertEquals(min(listOfCells), Cell.Number(0))
  }
  test("maximum with empty list") {
    val listOfCells = List()

    assertEquals(min(), Cell.Empty)
  }

  //-------------------------------Function "maximum"----------------------------------\\
  test("maximum with only numbers") {
    val cell1 = Cell.Number(3)
    val cell2 = Cell.Number(2)
    val cell3 = Cell.Number(4)
    val cell4 = Cell.Number(1)
    val listOfCells = List(cell1, cell2, cell3, cell4)

    assertEquals(max(listOfCells), Cell.Number(4))
  }
  test("maximum with text") {
    val cell1 = Cell.Number(3)
    val cell2 = Cell.Text("2")
    val cell3 = Cell.Number(1)
    val cell4 = Cell.Number(4)
    val listOfCells = List(cell1, cell2, cell3, cell4)

    assertEquals(max(listOfCells), Cell.ErrorCell)
  }
  test("maximum with empty") {
    val cell1 = Cell.Number(3)
    val cell2 = Cell.Empty
    val cell3 = Cell.Number(2)
    val cell4 = Cell.Number(4)
    val listOfCells = List(cell1, cell2, cell3, cell4)

    assertEquals(max(listOfCells), Cell.Number(4))
  }

  test("maximum with all empty") {
    val cell1 = Cell.Empty
    val cell2 = Cell.Empty
    val cell3 = Cell.Empty
    val cell4 = Cell.Empty
    val listOfCells = List(cell1, cell2, cell3, cell4)

    assertEquals(max(listOfCells), Cell.Number(0))
  }
  test("maximum with empty list") {
    val listOfCells = List()

    assertEquals(max(), Cell.Empty)
  }

  //-------------------------------Function "ifCond"----------------------------------\\
  test("if with number=number as condition"){
    val condition = "1=1"

    assertEquals(ifCond(condition, "oui","non"), Cell.Text("oui"))
  }

  test("if with number=Text as condition"){
    val condition = "1=hello"

    assertEquals(ifCond(condition, "oui","non"), Cell.Text("non"))
  }

  test("if with number=otherNumber as condition"){
    val condition = "1=2"

    assertEquals(ifCond(condition, "oui","non"), Cell.Text("non"))
  }

  test("if with Text=otherText as condition"){
    val condition = "Hello=hello"

    assertEquals(ifCond(condition, "oui","non"), Cell.Text("non"))
  }

  test("if with empty string as condition"){
    val condition = ""

    assertEquals(ifCond(condition, "oui","non"), Cell.Text("non"))
  }

  test("if with only text as condition"){
    val condition = "hello"

    assertEquals(ifCond(condition, "oui","non"), Cell.ErrorCell)
  }

  test("if with number different 0 as condition"){
    val condition = "150"

    assertEquals(ifCond(condition, "oui","non"), Cell.Text("oui"))
  }

  test("if with single digit different 0 as condition"){
    val condition = "150"

    assertEquals(ifCond(condition, "oui","non"), Cell.Text("oui"))
  }

  test("if with negative number as condition"){
    val condition = "-150"

    assertEquals(ifCond(condition, "oui","non"), Cell.Text("oui"))
  }

  test("if with 0 as condition"){
    val condition = "0"

    assertEquals(ifCond(condition, "oui","non"), Cell.Text("non"))
  }
  test("if with special character as condition"){
    val condition = ">"

    assertEquals(ifCond(condition, "oui","non"), Cell.Text("non"))
  }


}
