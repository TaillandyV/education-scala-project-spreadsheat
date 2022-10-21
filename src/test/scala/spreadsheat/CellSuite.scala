package spreadsheat

import spreadsheat.Cell

class CellSuite extends munit.FunSuite {
  test("error cell should return #error") {
    val cell = Cell.Error

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
    println(Cell.stringToCell(cell))
    println(Cell.Number(12))

    assertEquals(Cell.stringToCell(cell), Cell.Number(124))
  }
  test("string beginning with apostrophe + number") {
    val cell = "'124Hello"

    assertEquals(Cell.stringToCell(cell), Cell.Text("124Hello"))
  }
  test("string beginning with number") {
    val cell = "124hello"

    assertEquals(Cell.stringToCell(cell), Cell.Error)
  }
  test("string beginning with letter lower case") {
    val cell = "hello"

    assertEquals(Cell.stringToCell(cell), Cell.Text("hello"))
  }
  test("string beginning with letter maj case") {
    val cell = "Hello"

    assertEquals(Cell.stringToCell(cell), Cell.Text("Hello"))
  }
}
