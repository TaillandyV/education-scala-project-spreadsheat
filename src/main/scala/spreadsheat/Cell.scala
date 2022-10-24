package spreadsheat

import spreadsheat.Cell.stringToBooleen


enum Cell:
  case Number (double: Double)
  case Text (string : String)
  case Booleen (bool : Boolean)
  case Empty
  case ErrorCell

  def show : String =
    this match
      case Number(double) => double.toString
      case Text(string) => string.toString
      case Booleen(bool) => bool.toString
      case Empty => " " * 5
      case ErrorCell => "#ERROR"

  def getValue : Either[String,Either[Double, Boolean]] =
    val DoubleValue : Either[String,Either[Double, Boolean]] = Left(this.show)
    this match
      case Number(double) => Right(Left(this.show.toDouble))
      case Text(string) => Left(this.show)
      case Booleen(bool) => Right(Right(this.show.toBoolean))
      case Empty => Left(null)
      case ErrorCell => Left("precedent")

  def getNum : Option[Double] =
    this.show.toDoubleOption

  def getText : String =
    this.show

  def getBool : Boolean =
    this.show.toBoolean



//companion object
object Cell :
  def stringToNumber(string: String): Option[Cell] =
    string.toDoubleOption.map(Double => Cell.Number(Double))

  def stringToBooleen(string: String): Option[Cell] =
    string match {
      case "true" => Some(Cell.Booleen(true))
      case "false" => Some(Cell.Booleen(false))
      case _ => None
    }

  def stringToText(string: String): Option[Cell] =
    val patternBeginDigit = "^'[0-9][a-zA-Z0-9]*".r
    val patternBeginLetter = "^[a-zA-Z]*".r
    string match {
      case patternBeginDigit() => Some(Cell.Text(string.substring(1)))
      case patternBeginLetter() => Some(Cell.Text(string))
      case _ => None
    }

  def stringToCell(string: String): Cell = {
    stringToNumber(string) orElse stringToBooleen(string) orElse stringToText(string) getOrElse Cell.ErrorCell
  }

  def isCellNum(cell: Cell): Boolean = {
    cell.getClass.toString match {
      case "class spreadsheat.Cell$Number" => true
      case _ => false
    }
  }

  def isCellText(cell: Cell): Boolean = {
    cell.getClass.toString match {
      case "class spreadsheat.Cell$Text" => true
      case _ => false
    }
  }

  def isCellBool(cell: Cell): Boolean = {
    cell.getClass.toString match {
      case "class spreadsheat.Cell$Booleen" => true
      case _ => false
    }
  }

  def isCellEmpty(cell: Cell): Boolean = {
    if(cell.show == "     ") true
    else false
    /*cell.getClass.toString match {
      case "class spreadsheat.Cell$$anon$1" => true
      case _ => false
    }*/
  }

  def isCellError(cell: Cell): Boolean = {
    if(cell.show == "#ERROR") true
    else false
    /*cell.getClass.toString match {
      case "class spreadsheat.Cell$$anon$1" => true
      case _ => false
    }*/
  }
