package spreadsheat

case class Spreadsheet(rows: List[Row]) {
  def show: Unit = rows.zipWithIndex.foreach{
    case(row,idx) =>
      print(idx)
      row.show
      print("\n")
  }

  def add(rowIndex: Int, colIndex: Int, newCell: Cell): Spreadsheet =
    copy(rows = rows.updated(rowIndex, rows(rowIndex).add(colIndex, newCell)))

  def getCell(rowIndex: Int, colIndex: Int): Cell =
    rows(rowIndex).getCell(colIndex)
}

object Spreadsheet {
  def empty(width: Int, height: Int): Spreadsheet = Spreadsheet((0 until height).map(_ => Row.empty(width)).toList)
}

