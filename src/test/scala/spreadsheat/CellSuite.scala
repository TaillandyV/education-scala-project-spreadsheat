package spreadsheat

import spreadsheat.Cell

class CellSuite extends munit.FunSuite {
  test("error cell shopuld return #error") {
    val cell = Cell.Error

    assertEquals(cell.eval, "#ERROR")
  }
}
