package spreadsheat

enum Cell:
  case Number (double: Double)
  case Text (string : String)
  case Booleen (bool : Boolean)
  case Empty
  case Error

  def show : String =
    this match
      case Number(double) => double.toString
      case Text(string) => string.toString
      case Booleen(bool) => bool.toString
      case Empty => " " * 5
      case Error => "#ERROR"

  def isNum : Boolean =
    this match
      case Number(double) => true
      case Text(string) => false
      case Booleen(bool) => false
      case Error => false

//companion object
object Cell :
  def stringToNumber(string: String): Option[Cell] =
    string.toFloatOption.map(float => Cell.Number(float))

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
    stringToNumber(string) orElse stringToBooleen(string) orElse stringToText(string) getOrElse Cell.Error
  }
