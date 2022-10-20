package spreadsheat

case class Row(cells: List[Cell]) {
  def show: Unit = cells.foreach {
    cell => print(s"|${cell.show}|")
  }

  def add(index: Int, value: Float): Row =
    copy(cells = cells.updated(index, Cell.Number(value)))
}
object Row {
  def empty(width: Int): Row = Row((0 until width).map(_ => Cell.Empty).toList)
}

