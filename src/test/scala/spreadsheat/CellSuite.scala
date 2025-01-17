package spreadsheat

import spreadsheat.Cell

class CellSuite extends munit.FunSuite {
  test("error cell should return #error") {
    val cell = Cell.ErrorCell

    assertEquals(cell.show, "#ERROR")
  }
  
  test("Cell is a number") {
    val cell = 124

    assertEquals(Cell.Number(cell).show, "124.0")
  }
  
  test("Cell is a boolean") {
    val cell = true

    assertEquals(Cell.Booleen(cell).show, "true")
  }

  test("Cell is a negative number") {
    val cell = -124

    assertEquals(Cell.Number(cell).show, "-124.0")
  }

  test("cell is a number ?") {
    val cell = "124"
    assertEquals(Cell.stringToCell(cell), Cell.Number(124))
  }

  test("string beginning with apostrophe + number") {
    val cell = "'124Hello"

    assertEquals(Cell.stringToCell(cell), Cell.Text("124Hello"))
  }

  test("string beginning with number") {
    val cell = "124hello"

    assertEquals(Cell.stringToCell(cell), Cell.ErrorCell)
  }

  test("string beginning with letter lower case") {
    val cell = "hello"

    assertEquals(Cell.stringToCell(cell), Cell.Text("hello"))
  }

  test("string beginning with letter upper case") {
    val cell = "Hello"

    assertEquals(Cell.stringToCell(cell), Cell.Text("Hello"))
  }

  test("string beginning with letter and finishing with digit") {
    val cell = "Hello123"

    assertEquals(Cell.stringToCell(cell), Cell.Text("Hello123"))
  }

  test("cell is a number via isCellNum ") {
    val cell = Cell.Number(124)
    val cell2 = Cell.Text("hello")

    assertEquals(Cell.isCellNum(cell), true)
    assertEquals(Cell.isCellNum(cell2), false)
  }

  test("cell is a text via isCellText ") {
    val cell = Cell.Number(124)
    val cell2 = Cell.Text("hello")

    assertEquals(Cell.isCellText(cell), false)
    assertEquals(Cell.isCellText(cell2), true)
  }
  
  test("cell is a bool via isCellBool ") {
    val cell = Cell.Number(124)
    val cell2 = Cell.Booleen(true)

    assertEquals(Cell.isCellBool(cell), false)
    assertEquals(Cell.isCellBool(cell2), true)
  }
  
  test("cell is empty via isCellEmpty ") {
    val cell = Cell.ErrorCell
    val cell2 = Cell.Empty
    assertEquals(Cell.isCellEmpty(cell), false)
    assertEquals(Cell.isCellEmpty(cell2), true)
  }
  
  test("cell is an error via isCellError ") {
    val cell = Cell.Number(124)
    val cell2 = Cell.ErrorCell

    assertEquals(Cell.isCellError(cell), false)
    assertEquals(Cell.isCellError(cell2), true)
  }

  test("check difference between isCellEmpty and isCellError") {
    val cell = Cell.Empty
    val cell2 = Cell.ErrorCell

    assertEquals(Cell.isCellError(cell), false)
    assertEquals(Cell.isCellEmpty(cell), true)
    assertEquals(Cell.isCellError(cell2), true)
    assertEquals(Cell.isCellEmpty(cell2), false)
  }

  test("test getFunctions") {
    val cell = Cell.Number(124)
    val cell2 = Cell.Text("hello")
    val cell3 = Cell.Booleen(true)

    assertEquals(cell.getNum.get, 124.0)
    assertEquals(cell2.getText, "hello")
    assertEquals(cell3.getBool, true)
  }
}
