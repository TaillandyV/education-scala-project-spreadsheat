package spreadsheat

case class Spreadsheet(rows: List[Row]) {
  def show: Unit = rows.foreach{
    row => {
      row.show
      print("\n")
    }
  }

  def add(rowIndex: Int, colIndex: Int, value: Float): Spreadsheet =
    copy(rows = rows.updated(rowIndex, rows(rowIndex).add(colIndex, value)))
}

object Spreadsheet {
  def empty(width: Int, height: Int): Spreadsheet = Spreadsheet((0 until height).map(_ => Row.empty(width)).toList)
}

