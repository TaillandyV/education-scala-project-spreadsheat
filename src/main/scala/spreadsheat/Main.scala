package spreadsheat

@main
def main(): Unit = {
  val spreadSheet = Spreadsheet.empty(10, 10)
  spreadSheet.show
  println("\n\n")
  val updatedSpreadSheet = spreadSheet.add(1, 1, 20.0)

  updatedSpreadSheet.show
}