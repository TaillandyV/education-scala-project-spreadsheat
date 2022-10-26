package spreadsheat

enum CellType:
  case Number, Text, Booleen, Empty, ErrorCell


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

  def getType: CellType =
    this match
      case Cell.Number(_)  => CellType.Number
      case Cell.Text(_)    => CellType.Text
      case Cell.Booleen(_) => CellType.Booleen
      case Cell.Empty      => CellType.Empty
      case Cell.ErrorCell  => CellType.ErrorCell

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
    val patternBeginLetter = "^[a-zA-Z][a-zA-Z0-9]*".r
    string match {
      case patternBeginDigit() => Some(Cell.Text(string.substring(1)))
      case patternBeginLetter() => Some(Cell.Text(string))
      case _ => None
    }

  def stringToCell(string: String): Cell = {
    stringToNumber(string) orElse stringToBooleen(string) orElse stringToText(string) getOrElse Cell.ErrorCell
  }

  def isCellNum(cell: Cell): Boolean = {
    if(cell.getType == CellType.Number) true
    else false
  }
  
  def isCellText(cell: Cell): Boolean = {
    if(cell.getType == CellType.Text) true
    else false
  }

  def isCellBool(cell: Cell): Boolean = {
    if(cell.getType == CellType.Booleen) true
    else false
  }

  def isCellEmpty(cell: Cell): Boolean = {
    if(cell.getType == CellType.Empty) true
    else false
  }

  def isCellError(cell: Cell): Boolean = {
    if(cell.getType == CellType.ErrorCell) true
    else false
  }
