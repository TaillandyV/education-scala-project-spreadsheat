package spreadsheat

import spreadsheat.Cell
import spreadsheat.Row


class RowSuite extends munit.FunSuite {
  test("Should return size of 9") {
    val first_row = new Row(9)
    println(first_row.get_size())
  }
}
