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

    Cell.stringToCell(cell) == Cell.Number(124)
  }
}
