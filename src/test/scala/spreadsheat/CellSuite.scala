package spreadsheat

import spreadsheat.Cell

class CellSuite extends munit.FunSuite {
  test("error cell should return #error") {
    val cell = Cell.Error

    assertEquals(cell.eval, "#ERROR")
  }
  test("Cell is a number") {
    val cell = 124

    assertEquals(Cell.Number(cell).eval, "124.0")
  }
}
