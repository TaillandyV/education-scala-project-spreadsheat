package spreadsheat
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

class Row(size_row_init:Int) {
  //Row var
  var size_row: Int = size_row_init
  var list_of_cell: ListBuffer[Cell] = ListBuffer()
  for (i <- 0 to size_row)
    list_of_cell.append(Cell.Text("Na"))

  //Row method
  def get_size(): Int ={
    size_row
  }
}
