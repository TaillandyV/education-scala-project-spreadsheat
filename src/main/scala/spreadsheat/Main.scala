package spreadsheat
import scala.collection.mutable.ListBuffer
import scala.io.StdIn.readLine
import scala.concurrent.*

val promise: Promise[Boolean] = Promise()
val running : Future[Boolean] = promise.future
val colTable = (0 until 26).map(index => (index + 'A').toChar.toString -> index).toMap

val cellCoordinate = "^[A-Z]+[0-9]+".r
val areaCoordinate = "^[A-Z]+[0-9]+:[A-Z]+[0-9]+".r
val numberCell = "^-?[0-9]+".r


@main
def main(): Unit = {

  var spreadSheet = Spreadsheet.empty(5, 5)
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

  val assignPattern = "^[A-Z]+[0-9]+=(?!SUM)(?!CONCAT)(?!MIN)(?!MAX)(?!COUNT)(?!IF).*".r

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
      sumFunction(inputString,spreadSheet)
    case concatPattern() =>
      println("Doing concat")
      concatFunction(inputString,spreadSheet)
    case minPattern() =>
      println("Doing min")
      minFunction(inputString,spreadSheet)
    case maxPattern() =>
      println("Doing max")
      maxFunction(inputString, spreadSheet)
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
def evaluateCell(input:String,spreadsheet: Spreadsheet):List[Cell]={
  val listCell = new ListBuffer[Cell]()
  input match{
    case numberCell() =>
      listCell += Cell.stringToCell(input)
      listCell.toList
    case cellCoordinate()=>
      val (row,col) = extractCoordinate(input)
      listCell += spreadsheet.getCell(row,col)
      listCell.toList
    case areaCoordinate() =>
      val cell1:String = input.split(":")(0)
      val cell2:String = input.split(":")(1)

      val rowBorder1 = "[A-Z]+".r.split(cell1)(1).toInt
      val rowBorder2 = "[A-Z]+".r.split(cell2)(1).toInt
      val colBorder1 = colTable.getOrElse("[0-9]+".r.split(cell1)(0), 0)
      val colBorder2 = colTable.getOrElse("[0-9]+".r.split(cell2)(0), 0)

      val listCoordinateCells = for col <- colBorder1 to colBorder2 yield for row <- rowBorder1 to rowBorder2 yield (row,col)
      val listCell = listCoordinateCells.map{cellCoordinate =>
        val rowCell = cellCoordinate.map{cell =>
          spreadsheet.getCell(cell(0),cell(1))
        }
        rowCell.toList
      }
      listCell.toList.flatten
  }
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
    addList.foreach{operation=>
      println(operation)
    }
    spreadSheet
  }
  else {
    addList(0) match{
      case cellCoordinate() =>
        val (corRow, corCol) = extractCoordinate(addList(0))
        spreadSheet.add(row, col, spreadSheet.getCell(corRow,corCol))
      case _ =>
        spreadSheet.add(row, col, Cell.stringToCell(addList(0)))
    }
  }
}

def sumFunction(input:String, spreadSheet: Spreadsheet): Spreadsheet = {
  val (row,col,value) = extractCommand(input)
  val valueCleared = value.substring(4, value.length - 1)
  val sumList = valueCleared.split(",")
  val cellList = sumList.map{coordinate =>
    evaluateCell(coordinate,spreadSheet)
  }
  spreadSheet.add(row,col,sum(cellList.flatten.toList))
}

def concatFunction(input:String, spreadSheet: Spreadsheet): Spreadsheet = {
  val (row,col,value) = extractCommand(input)
  val valueCleared = value.substring(7, value.length - 1)
  val concatList = valueCleared.split(",")
  val cellList = concatList.map{coordinate =>
    evaluateCell(coordinate,spreadSheet)
  }
  val cells = cellList.flatten
  spreadSheet.add(row,col,concat(cells(0),cells(1)))
}

def minFunction(input:String, spreadSheet: Spreadsheet): Spreadsheet = {
  val (row,col,value) = extractCommand(input)
  val valueCleared = value.substring(4, value.length - 1)
  val minList = valueCleared.split(",")
  val cellList = minList.map{coordinate =>
    evaluateCell(coordinate,spreadSheet)
  }
  spreadSheet.add(row,col,min(cellList.flatten.toList))
}

def maxFunction(input:String, spreadSheet: Spreadsheet): Spreadsheet = {
  val (row,col,value) = extractCommand(input)
  val valueCleared = value.substring(4, value.length - 1)
  val minList = valueCleared.split(",")
  val cellList = minList.map{coordinate =>
    evaluateCell(coordinate,spreadSheet)
  }
  spreadSheet.add(row,col,max(cellList.flatten.toList))
}