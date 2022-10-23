package spreadsheat
import scala.io.StdIn.readLine
import scala.concurrent.*

val promise: Promise[Boolean] = Promise()
val running : Future[Boolean] = promise.future
val colTable = (0 until 26).map(index => (index + 'A').toChar.toString -> index).toMap

@main
def main(): Unit = {

  val spreadSheet = Spreadsheet.empty(3, 3)
  spreadSheet.show

  while(!running.isCompleted){
    println("\n\n")
    println("Que voulez-vous faire ?")
    val input = readLine()
    getCommand(input,spreadSheet)
  }

}

def getCommand(inputString: String,spreadSheet: Spreadsheet):Unit={

  val assignPattern = "^[A-Z]+[0-9]+=(?:[A-Z]+[0-9]+[+*-/]|-?[0-9]+[+*-/])*(?:[A-Z]+[0-9]+|-?[0-9]+)".r

  val sumPattern = "^[A-Z]+[0-9]+=SUM\\((?:[A-Z]+[0-9]+[:][A-Z]+[0-9]+|(?:-?[0-9]+[,]|[A-Z]+[0-9]+[,]|[A-Z]+[0-9]+[:][A-Z]+[0-9]+[,])+(?:-?[0-9]+|[A-Z]+[0-9]+|[A-Z]+[0-9]+[:][A-Z]+[0-9]+))\\)".r

  val concatPattern = "^[A-Z]+[0-9]+=CONCAT\\((?:[A-Z]+[0-9]+|\".*\"),(?:[A-Z]+[0-9]+|\".*\")\\)".r

  val minPattern = "^[A-Z]+[0-9]+=MIN\\((?:[A-Z]+[0-9]+[:][A-Z]+[0-9]+|(?:[0-9]+[,]|[A-Z]+[0-9]+[,]|[A-Z]+[0-9]+[:][A-Z]+[0-9]+[,])+(?:[0-9]+|[A-Z]+[0-9]+|[A-Z]+[0-9]+[:][A-Z]+[0-9]+))\\)".r

  val maxPattern = "^[A-Z]+[0-9]+=MAX\\((?:[A-Z]+[0-9]+[:][A-Z]+[0-9]+|(?:[0-9]+[,]|[A-Z]+[0-9]+[,]|[A-Z]+[0-9]+[:][A-Z]+[0-9]+[,])+(?:[0-9]+|[A-Z]+[0-9]+|[A-Z]+[0-9]+[:][A-Z]+[0-9]+))\\)".r

  val countPattern = "^[A-Z]+[0-9]+=COUNT\\((?:[A-Z]+[0-9]+[:][A-Z]+[0-9]+|(?:[0-9]+[,]|[A-Z]+[0-9]+[,]|[A-Z]+[0-9]+[:][A-Z]+[0-9]+[,])+(?:[0-9]+|[A-Z]+[0-9]+|[A-Z]+[0-9]+[:][A-Z]+[0-9]+))\\)".r

  val ifPattern = "^[A-Z]+[0-9]+=IF\\((?:\"[a-zA-Z0-9]*\"|-?[0-9]*|[A-Z]+[0-9]+),.+,.+\\)".r

  inputString match {
    case "EXIT" => promise.success(true)
    case assignPattern() => addValue(inputString,colTable,spreadSheet).show
    case sumPattern() => println("Doing sum")
    case concatPattern() => println("Doing concat")
    case minPattern() => println("Doing min")
    case maxPattern() => println("Doing max")
    case ifPattern() => println("Doing if")
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

  val additionList = value.split("[+-]")

  if (additionList.size >=2){
    additionList.foreach{ x=>
      println(x)
    }
    spreadSheet
  }
  else  spreadSheet.add(row, col, Cell.stringToCell(additionList(0)))
}