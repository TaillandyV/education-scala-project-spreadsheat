package spreadsheat

import spreadsheat.Cell.isCellNum
import spreadsheat.Cell.isCellEmpty
import spreadsheat.Cell.isCellText
import spreadsheat.Cell

def compareCompute(value1: Double, operator: String, value2: Double): Boolean =
  operator match {
    case "<" => value1 < value2
    case ">" => value1 > value2
    case "=" => value1 == value2
    case ">=" => value1 >= value2
    case "<=" => value1 <= value2
  }

def compareOperator(cell1: Cell, operator:String, cell2: Cell):Cell =
  if (isCellNum(cell1) && isCellNum(cell2)) {
    Cell.Booleen(compareCompute(cell1.getNum.get,operator,cell2.getNum.get))
  }
  else if (isCellEmpty(cell1) && isCellNum(cell2)) {
    Cell.Booleen(compareCompute(0.0,operator,cell2.getNum.get))
  }
  else if (isCellNum(cell1) && isCellEmpty(cell2)) {
    Cell.Booleen(compareCompute(cell1.getNum.get,operator,0.0))
  }
  else Cell.ErrorCell



def calculateCompute(value1: Double, operator: String, value2: Double): Double =
  operator match {
    case "+" => value1 + value2
    case "-" => value1 - value2
    case "*" => value1 * value2
    case "/" => value1 / value2
  }

def calculateOperator(cell1: Cell, operator:String, cell2: Cell):Cell =
  if (isCellNum(cell1) && isCellNum(cell2)) {
    if(cell2.getNum.get == 0.0 && operator =="/")
      Cell.ErrorCell
    else
      Cell.Number(calculateCompute(cell1.getNum.get, operator, cell2.getNum.get))
  }
  else if (isCellEmpty(cell1) && isCellNum(cell2)) {
    Cell.Number(calculateCompute(0.0, operator, cell2.getNum.get))
  }
  else if (isCellNum(cell1) && isCellEmpty(cell2)) {
    if(operator == "/")
      Cell.ErrorCell
    else
      Cell.Number(calculateCompute(cell1.getNum.get, operator, 0.0))
  }
  else Cell.ErrorCell

def sum(listOfCell: List[Cell]): Cell =
  var res : Double = 0
  listOfCell.zipWithIndex.foreach{
    case(value, idx)=>
      if(isCellNum(value)){
        res = res + value.getNum.get
      }
      else if(isCellEmpty(value)){
      }
      else if(!isCellNum(value)) {
        return Cell.ErrorCell
      }
  }
  Cell.Number(res)

def concat(cell1 : Cell, cell2:Cell) : Cell =
  if(isCellText(cell1) && isCellText(cell2)){
    Cell.Text(cell1.getText + cell2.getText)
  }
  else if (isCellEmpty(cell1) && isCellText(cell2)){
    Cell.Text(cell2.getText)
  }
  else if (isCellEmpty(cell2) && isCellText(cell1)){
    Cell.Text(cell1.getText)
  }
  else if (isCellEmpty(cell1) && isCellNum(cell2)){
    Cell.Number(cell2.getNum.get)
  }
  else if (isCellEmpty(cell2) && isCellNum(cell1)){
    Cell.Number(cell1.getNum.get)
  }
  else if (isCellNum(cell1) && isCellNum(cell2)){
    Cell.Number((cell1.getNum.get.toInt.toString + cell2.getNum.get.toInt.toString).toDouble)
  }
  else Cell.ErrorCell

def min(listOfCell: List[Cell]): Cell =
  var mini: Double = Double.MaxValue
  listOfCell.zipWithIndex.foreach{
    case(value, idx)=>
      if(isCellNum(value)){
        if(value.getNum.get < mini){
          mini = value.getNum.get
        }
      }
      else if(isCellEmpty(value)){
      }
      else if(!isCellNum(value)) {
        return Cell.ErrorCell
      }
  }
  Cell.Number(mini)

def max(listOfCell: List[Cell]): Cell =
  var maxi: Double = Double.MinValue
  listOfCell.zipWithIndex.foreach{
    case(value, idx)=>
      if(isCellNum(value)){
        if(value.getNum.get > maxi){
          maxi = value.getNum.get
        }
      }
      else if(isCellEmpty(value)){
      }
      else if(!isCellNum(value)) {
        return Cell.ErrorCell
      }
  }
  Cell.Number(maxi)

