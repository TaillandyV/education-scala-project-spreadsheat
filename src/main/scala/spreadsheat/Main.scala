package spreadsheat
import scala.io.StdIn.readLine

@main
def main(): Unit = {

  val colTable = (0 until 26).map(index => (index + 'A').toChar.toString -> index).toMap

  var running = true

  val spreadSheet = Spreadsheet.empty(3, 3)
  spreadSheet.show


  while(running){
    println("\n\n")
    println("Que voulez-vous faire ?")
    val input = readLine()
    input match {
      case "EXIT" => running = false
      case _ => val updatedSpreadSheet = addValue(input,colTable,spreadSheet)
        updatedSpreadSheet.show
    }
  }



}

def addValue(input:String,colTable:Map[String,Int],spreadSheet: Spreadsheet): Spreadsheet = {
  val command = input.split("=")
  val location = command(0)
  val value = command(1)

  val col = colTable.getOrElse("[0-9]+".r.split(location)(0), 0)
  val row = "[A-Z]+".r.split(location)(1).toInt


  spreadSheet.add(row, col, value.toFloat)
}