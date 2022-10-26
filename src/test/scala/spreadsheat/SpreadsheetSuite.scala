package spreadsheat

import spreadsheat.Cell

class SpreadsheetSuite extends munit.FunSuite {
  test("Assign a num value of 2 to cell in spreadsheet") {
    val spreadSheet = Spreadsheet.empty(5, 5)
    val updatedSpreadSheet = getCommand("A1=2",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.Number(2))
  }

  test("Assign a negative num value -2 to cell in spreadsheet") {
    val spreadSheet = Spreadsheet.empty(5, 5)
    val updatedSpreadSheet = getCommand("A1=-2",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.Number(-2))
  }

  test("Assign a string value of 2 to cell in spreadsheet") {
    val spreadSheet = Spreadsheet.empty(5, 5)
    val updatedSpreadSheet = getCommand("A1='2",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.Text("2"))
  }

  test("Assign a string value hello to cell in spreadsheet") {
    val spreadSheet = Spreadsheet.empty(5, 5)
    val updatedSpreadSheet = getCommand("A1=hello",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.Text("hello"))
  }


  test("Assign a boolean value true to cell in spreadsheet") {
    val spreadSheet = Spreadsheet.empty(5, 5)
    val updatedSpreadSheet = getCommand("A1=true",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.Booleen(true))
  }

  test("Assign a cell value to another cell in spreadsheet") {
    var spreadSheet = Spreadsheet.empty(5, 5)
    spreadSheet = getCommand("A2=2",spreadSheet)
    val updatedSpreadSheet = getCommand("A1=A2",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.Number(2))
  }

  test("Assign an addition between two number to a cell in spreadsheet") {
    val spreadSheet = Spreadsheet.empty(5, 5)
    val updatedSpreadSheet = getCommand("A1=1+2",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.Number(3))
  }
  test("Assign an addition between one number and one cell to a cell in spreadsheet") {
    var spreadSheet = Spreadsheet.empty(5, 5)
    spreadSheet = getCommand("A2=2",spreadSheet)
    val updatedSpreadSheet = getCommand("A1=1+A2",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.Number(3))
  }

  test("Assign an addition between two cell to a cell in spreadsheet") {
    var spreadSheet = Spreadsheet.empty(5, 5)
    spreadSheet = getCommand("A2=2",spreadSheet)
    val updatedSpreadSheet = getCommand("A1=A2+A2",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.Number(4))
  }
  test("Assign an addition between text and numer in spreadsheet") {
    var spreadSheet = Spreadsheet.empty(5, 5)
    spreadSheet = getCommand("A2=2",spreadSheet)
    val updatedSpreadSheet = getCommand("A1=A2+hello",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.ErrorCell)
  }

  test("Assign a soustraction between two cell to a cell in spreadsheet") {
    var spreadSheet = Spreadsheet.empty(5, 5)
    spreadSheet = getCommand("A2=2",spreadSheet)
    val updatedSpreadSheet = getCommand("A1=A2-A2",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.Number(0))
  }

  test("Assign a multiplication between one cell and number in spreadsheet") {
    var spreadSheet = Spreadsheet.empty(5, 5)
    spreadSheet = getCommand("A2=2",spreadSheet)
    val updatedSpreadSheet = getCommand("A1=A2*2",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.Number(4))
  }

  test("Assign a division between cell and number in spreadsheet") {
    var spreadSheet = Spreadsheet.empty(5, 5)
    spreadSheet = getCommand("A2=2",spreadSheet)
    val updatedSpreadSheet = getCommand("A1=A2/2",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.Number(1))
  }

  test("Assign a cell for division between cell and 0 in spreadsheet") {
    var spreadSheet = Spreadsheet.empty(5, 5)
    spreadSheet = getCommand("A2=2",spreadSheet)
    val updatedSpreadSheet = getCommand("A1=A2/0",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.ErrorCell)
  }

  test("Assign a cell check for equality to a cell in spreadsheet") {
    var spreadSheet = Spreadsheet.empty(5, 5)
    spreadSheet = getCommand("A2=2",spreadSheet)
    val updatedSpreadSheet = getCommand("A1=A2=A2",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.Booleen(true))
  }

  test("Assign a cell check for sup to a cell in spreadsheet") {
    var spreadSheet = Spreadsheet.empty(5, 5)
    spreadSheet = getCommand("A2=2",spreadSheet)
    val updatedSpreadSheet = getCommand("A1=A2>A2",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.Booleen(false))
  }
  test("Assign a cell check for inf to a cell in spreadsheet") {
    var spreadSheet = Spreadsheet.empty(5, 5)
    spreadSheet = getCommand("A2=2",spreadSheet)
    val updatedSpreadSheet = getCommand("A1=A2<A2",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.Booleen(false))
  }
  test("Assign a cell check for sup or equal to a cell in spreadsheet") {
    var spreadSheet = Spreadsheet.empty(5, 5)
    spreadSheet = getCommand("A2=2",spreadSheet)
    val updatedSpreadSheet = getCommand("A1=A2>=A2",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.Booleen(true))
  }
  test("Assign a cell check for sup or equal to a cell in spreadsheet") {
    var spreadSheet = Spreadsheet.empty(5, 5)
    spreadSheet = getCommand("A2=2",spreadSheet)
    val updatedSpreadSheet = getCommand("A1=A2<=A2",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.Booleen(true))
  }
  test("Assign a cell in spreadsheet") {
    var spreadSheet = Spreadsheet.empty(5, 5)
    spreadSheet = getCommand("A2=2",spreadSheet)
    val updatedSpreadSheet = getCommand("A1=A2<=A2",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.Booleen(true))
  }

  test("Assign a cell SUM two cell in spreadsheet") {
    var spreadSheet = Spreadsheet.empty(5, 5)
    spreadSheet = getCommand("A2=2",spreadSheet)
    spreadSheet = getCommand("A3=4",spreadSheet)
    val updatedSpreadSheet = getCommand("A1=SUM(A2,A3)",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.Number(6))
  }

  test("Assign a cell SUM area cell in spreadsheet") {
    var spreadSheet = Spreadsheet.empty(5, 5)
    spreadSheet = getCommand("A2=2",spreadSheet)
    spreadSheet = getCommand("A3=4",spreadSheet)
    spreadSheet = getCommand("B2=-2",spreadSheet)
    spreadSheet = getCommand("B3=-4",spreadSheet)
    val updatedSpreadSheet = getCommand("A1=SUM(A2:B3)",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.Number(0))
  }
  test("Assign a cell SUM area and single cell in spreadsheet") {
    var spreadSheet = Spreadsheet.empty(5, 5)
    spreadSheet = getCommand("A2=2",spreadSheet)
    spreadSheet = getCommand("A3=4",spreadSheet)
    spreadSheet = getCommand("B2=-2",spreadSheet)
    spreadSheet = getCommand("B3=-4",spreadSheet)
    val updatedSpreadSheet = getCommand("A1=SUM(A2:B3,A2)",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.Number(2))
  }
  test("Assign a cell SUM area and number in spreadsheet") {
    var spreadSheet = Spreadsheet.empty(5, 5)
    spreadSheet = getCommand("A2=2",spreadSheet)
    spreadSheet = getCommand("A3=4",spreadSheet)
    spreadSheet = getCommand("B2=-2",spreadSheet)
    spreadSheet = getCommand("B3=-4",spreadSheet)
    val updatedSpreadSheet = getCommand("A1=SUM(A2:B3,2)",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.Number(2))
  }
  test("Assign a cell SUM area and string in spreadsheet") {
    var spreadSheet = Spreadsheet.empty(5, 5)
    spreadSheet = getCommand("A2=2",spreadSheet)
    spreadSheet = getCommand("A3=4",spreadSheet)
    spreadSheet = getCommand("B2=-2",spreadSheet)
    spreadSheet = getCommand("B3=hello",spreadSheet)
    val updatedSpreadSheet = getCommand("A1=SUM(A2:B3,2)",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.ErrorCell)
  }

  test("Assign a cell CONCAT text cell and text cell in spreadsheet") {
    var spreadSheet = Spreadsheet.empty(5, 5)
    spreadSheet = getCommand("A2=hello",spreadSheet)
    spreadSheet = getCommand("A3=world",spreadSheet)
    val updatedSpreadSheet = getCommand("A1=CONCAT(A2,A3)",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.Text("helloworld"))
  }
  test("Assign a cell CONCAT text cell and number cell in spreadsheet") {
    var spreadSheet = Spreadsheet.empty(5, 5)
    spreadSheet = getCommand("A2=hello",spreadSheet)
    spreadSheet = getCommand("A3=2",spreadSheet)
    val updatedSpreadSheet = getCommand("A1=CONCAT(A2,A3)",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.ErrorCell)
  }
  test("Assign a cell CONCAT number cell and number cell in spreadsheet") {
    var spreadSheet = Spreadsheet.empty(5, 5)
    spreadSheet = getCommand("A2=1",spreadSheet)
    spreadSheet = getCommand("A3=2",spreadSheet)
    val updatedSpreadSheet = getCommand("A1=CONCAT(A2,A3)",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.Number(12))
  }
  test("Assign a cell MIN number cell and number cell in spreadsheet") {
    var spreadSheet = Spreadsheet.empty(5, 5)
    spreadSheet = getCommand("A2=1",spreadSheet)
    spreadSheet = getCommand("A3=2",spreadSheet)
    val updatedSpreadSheet = getCommand("A1=MIN(A2,A3)",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.Number(1))
  }
  test("Assign a cell MIN cell area and number in spreadsheet") {
    var spreadSheet = Spreadsheet.empty(5, 5)
    spreadSheet = getCommand("A2=2",spreadSheet)
    spreadSheet = getCommand("A3=4",spreadSheet)
    spreadSheet = getCommand("B2=-2",spreadSheet)
    spreadSheet = getCommand("B3=-4",spreadSheet)
    val updatedSpreadSheet = getCommand("A1=MIN(A2:B3,2)",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.Number(-4))
  }

  test("Assign a cell MAX number cell and number cell in spreadsheet") {
    var spreadSheet = Spreadsheet.empty(5, 5)
    spreadSheet = getCommand("A2=1",spreadSheet)
    spreadSheet = getCommand("A3=2",spreadSheet)
    val updatedSpreadSheet = getCommand("A1=MAX(A2,A3)",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.Number(2))
  }

  test("Assign a cell MAX cell area and number in spreadsheet") {
    var spreadSheet = Spreadsheet.empty(5, 5)
    spreadSheet = getCommand("A2=2",spreadSheet)
    spreadSheet = getCommand("A3=4",spreadSheet)
    spreadSheet = getCommand("B2=-2",spreadSheet)
    spreadSheet = getCommand("B3=-4",spreadSheet)
    val updatedSpreadSheet = getCommand("A1=MAX(A2:B3,2)",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.Number(4))
  }

  test("Assign a cell MAX cell area and number in spreadsheet") {
    var spreadSheet = Spreadsheet.empty(5, 5)
    spreadSheet = getCommand("A2=2",spreadSheet)
    spreadSheet = getCommand("A3=2",spreadSheet)
    spreadSheet = getCommand("B2=2",spreadSheet)
    spreadSheet = getCommand("B3=2/0",spreadSheet)
    val updatedSpreadSheet = getCommand("A1=COUNT(A2:B3)",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.Number(3))
  }

  test("Assign a cell IF in spreadsheet") {
    val spreadSheet = Spreadsheet.empty(5, 5)
    val updatedSpreadSheet = getCommand("A1=IF(1=1,oui,non)",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.Text("oui"))
  }
  test("Assign a cell IF in spreadsheet") {
    val spreadSheet = Spreadsheet.empty(5, 5)
    val updatedSpreadSheet = getCommand("A1=IF(1=2,oui,non)",spreadSheet)
    assertEquals(updatedSpreadSheet.getCell(1,0), Cell.Text("non"))
  }




}
