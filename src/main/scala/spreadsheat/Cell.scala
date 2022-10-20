package spreadsheat

enum Cell :
  case Number (value: Double)
  case Text (value : String)
  case Booleen (value : Boolean)
  case Empty
  case Error

  def show : String =
    this match
      case Number(value) => value.toString
      case Text(string) => string.toString
      case Booleen(bool) => bool.toString
      case Empty => " " * 5
      case Error => "#ERROR"
