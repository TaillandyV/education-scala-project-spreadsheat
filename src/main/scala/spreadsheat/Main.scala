package spreadsheat
import scala.io.StdIn.readLine
import scala.concurrent.*

val promise: Promise[Boolean] = Promise()
val running : Future[Boolean] = promise.future
val colTable = (0 until 26).map(index => (index + 'A').toChar.toString -> index).toMap

val cellCoordinate = "^[A-Z]+[0-9]+".r

@main
def main(): Unit = {

  var spreadSheet = Spreadsheet.empty(3, 3)
  spreadSheet.show

  while(!running.isCompleted){
    println("\n\n")
    println("Que voulez-vous faire ?")
    val input = readLine()
    val updatedSpreadsheet : Spreadsheet = getCommand(input,spreadSheet)
    updatedSpreadsheet.show
    spreadSheet = updatedSpreadsheet
  }

}

def getCommand(inputString: String,spreadSheet: Spreadsheet): Spreadsheet={

  val assignPattern = "^[A-Z]+[0-9]+=(?:[A-Z]+[0-9]+[+*-/]|-?[0-9]+[+*-/])*(?:[A-Z]+[0-9]+|-?[0-9]+)".r

  val sumPattern = "^[A-Z]+[0-9]+=SUM\\((?:[A-Z]+[0-9]+[:][A-Z]+[0-9]+|(?:-?[0-9]+[,]|[A-Z]+[0-9]+[,]|[A-Z]+[0-9]+[:][A-Z]+[0-9]+[,])+(?:-?[0-9]+|[A-Z]+[0-9]+|[A-Z]+[0-9]+[:][A-Z]+[0-9]+))\\)".r

  val concatPattern = "^[A-Z]+[0-9]+=CONCAT\\((?:[A-Z]+[0-9]+|\".*\"),(?:[A-Z]+[0-9]+|\".*\")\\)".r

  val minPattern = "^[A-Z]+[0-9]+=MIN\\((?:[A-Z]+[0-9]+[:][A-Z]+[0-9]+|(?:[0-9]+[,]|[A-Z]+[0-9]+[,]|[A-Z]+[0-9]+[:][A-Z]+[0-9]+[,])+(?:[0-9]+|[A-Z]+[0-9]+|[A-Z]+[0-9]+[:][A-Z]+[0-9]+))\\)".r

  val maxPattern = "^[A-Z]+[0-9]+=MAX\\((?:[A-Z]+[0-9]+[:][A-Z]+[0-9]+|(?:[0-9]+[,]|[A-Z]+[0-9]+[,]|[A-Z]+[0-9]+[:][A-Z]+[0-9]+[,])+(?:[0-9]+|[A-Z]+[0-9]+|[A-Z]+[0-9]+[:][A-Z]+[0-9]+))\\)".r

  val countPattern = "^[A-Z]+[0-9]+=COUNT\\((?:[A-Z]+[0-9]+[:][A-Z]+[0-9]+|(?:[0-9]+[,]|[A-Z]+[0-9]+[,]|[A-Z]+[0-9]+[:][A-Z]+[0-9]+[,])+(?:[0-9]+|[A-Z]+[0-9]+|[A-Z]+[0-9]+[:][A-Z]+[0-9]+))\\)".r

  val ifPattern = "^[A-Z]+[0-9]+=IF\\((?:\"[a-zA-Z0-9]*\"|-?[0-9]*|[A-Z]+[0-9]+),.+,.+\\)".r

  inputString match {
    case "EXIT" =>
      promise.success(true)
      spreadSheet
    case assignPattern() =>
      println("Doing assign")
      addValue(inputString,spreadSheet)
    case sumPattern() =>
      println("Doing sum")
      spreadSheet
    case concatPattern() =>
      println("Doing concat")
      spreadSheet
    case minPattern() =>
      println("Doing min")
      spreadSheet
    case maxPattern() =>
      println("Doing max")
      spreadSheet
    case ifPattern() =>
      println("Doing if")
      spreadSheet
    case _ =>
      println("Not a command")
      spreadSheet
  }
}

def extractCommand(input:String)={
  val command = input.split("=")
  val location = command(0)
  val value = command(1)
  val (row,col) = extractCoordinate(location)
  (row,col,value)
}

def extractCoordinate(input:String)={
  val col = colTable.getOrElse("[0-9]+".r.split(input)(0), 0)
  val row = "[A-Z]+".r.split(input)(1).toInt
  (row,col)
}

def addValue(input:String,spreadSheet: Spreadsheet): Spreadsheet = {

  val (row,col,value) = extractCommand(input)
  val addList = value.split("[+]")

  if (addList.size >= 2){
    addList.foreach{ operation=>
      println(operation)
    }
    spreadSheet
  }
  else {
    addList(0) match{
      case cellCoordinate() =>
        val (corRow, corCol) = extractCoordinate(addList(0))
        spreadSheet.add(row, col, spreadSheet.getCell(corRow,corCol))
      case _ => spreadSheet.add(row, col, Cell.stringToCell(addList(0)))
    }
  }
}
/*
def sumFunction(input:String,colTable:Map[String,Int],spreadSheet: Spreadsheet): Spreadsheet = {
  val (row,col,value) = extractCommand(input)
  val sumList = value.split("[,]")

}
*/