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
    getCommand(input)
  }

}

def getCommand(inputString: String):Unit={
  //(?:[A-Z]+[0-9]+|[0-9]+)
  val sumPattern = "^[A-Z]+[0-9]+=SUM\\((?:[A-Z]+[0-9]+[:][A-Z]+[0-9]+|(?:[0-9]+[,]|[A-Z]+[0-9]+[,]|[A-Z]+[0-9]+[:][A-Z]+[0-9]+[,])+(?:[0-9]+|[A-Z]+[0-9]+|[A-Z]+[0-9]+[:][A-Z]+[0-9]+))\\)".r

  val concatPattern = "^[A-Z]+[0-9]+=CONCAT\\([A-Z]+[0-9]+,[A-Z]+[0-9]+\\)".r

  inputString match {
    case "EXIT" => println("Exiting")
    case sumPattern() => println("Doing sum")
    case concatPattern() => println("Doing concat")
    case _ => print("Not a command")//val updatedSpreadSheet = addValue(input,colTable,spreadSheet)
    //updatedSpreadSheet.show
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