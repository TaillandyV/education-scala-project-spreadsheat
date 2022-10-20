package spreadsheat

enum Cell :
  case Number (value: Double)
  case Text (value : String)
  case Booleen (value : Boolean)
  case Error

  def eval : String =
    this match
      case Number(value) => value.toString
      case Text(string) => string.toString
      case Booleen(bool) => bool.toString
      case Error => "#ERROR"