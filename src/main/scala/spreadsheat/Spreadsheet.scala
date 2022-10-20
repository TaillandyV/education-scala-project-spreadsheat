package spreadsheat

case class Spreadsheet(rows: List[Row]) {
  def show: Unit = rows.foreach{
    row => {
      row.show
      print("\n")
    }
  }

  def add(x: Int, y: Int, value: Float): Spreadsheet =
    copy(rows = rows.updated(x, rows(x).add(y, value)))
}

object Spreadsheet {
  def empty(width: Int, height: Int): Spreadsheet = Spreadsheet((0 until height).map(_ => Row.empty(width)).toList)
}

