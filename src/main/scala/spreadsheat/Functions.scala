package spreadsheat

import spreadsheat.Cell.isCellNum
import spreadsheat.Cell.isCellEmpty
import spreadsheat.Cell.isCellText
import spreadsheat.Cell

def equals(l1 : Cell, l2: Cell): Boolean = l1.equals(l2)

def inf(cell1: Cell, cell2: Cell): Cell =
  if (isCellNum(cell1) && isCellNum(cell2)) {
    val a1 = cell1.getNum.get
    val a2 = cell2.getNum.get
    Cell.Booleen(a1 < a2)
  }
  else if (isCellEmpty(cell1)) {
    Cell.Number(cell2.getNum.get)
  }
  else if (isCellEmpty(cell2)) {
    Cell.Number(cell1.getNum.get)
  }
  else Cell.ErrorCell

def supp(cell1: Cell, cell2: Cell): Cell =
  if (isCellNum(cell1) && isCellNum(cell2)) {
    val a1 = cell1.getNum.get
    val a2 = cell2.getNum.get
    Cell.Booleen(a1 > a2)
  }
  else if (isCellEmpty(cell1)) {
    Cell.Number(cell2.getNum.get)
  }
  else if (isCellEmpty(cell2)) {
    Cell.Number(cell1.getNum.get)
  }
  else Cell.ErrorCell

def infOrEqual(cell1: Cell, cell2: Cell): Cell =
  if (isCellNum(cell1) && isCellNum(cell2)) {
    val a1 = cell1.getNum.get
    val a2 = cell2.getNum.get
    Cell.Booleen(a1 <= a2)
  }
  else if (isCellEmpty(cell1)) {
    Cell.Number(cell2.getNum.get)
  }
  else if (isCellEmpty(cell2)) {
    Cell.Number(cell1.getNum.get)
  }
  else Cell.ErrorCell

def supOrEqual(cell1: Cell, cell2: Cell): Cell =
  if (isCellNum(cell1) && isCellNum(cell2)) {
    val a1 = cell1.getNum.get
    val a2 = cell2.getNum.get
    Cell.Booleen(a1 >= a2)
  }
  else if (isCellEmpty(cell1)) {
    Cell.Number(cell2.getNum.get)
  }
  else if (isCellEmpty(cell2)) {
    Cell.Number(cell1.getNum.get)
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

def addition(cell1 : Cell, cell2:Cell) : Cell =
  if(isCellNum(cell1) && isCellNum(cell2)){
    Cell.Number(cell1.getNum.get + cell2.getNum.get)
  }
  else if (isCellEmpty(cell1) && isCellNum(cell2)){
    Cell.Number(cell2.getNum.get)
  }
  else if (isCellEmpty(cell2) && isCellNum(cell1)){
    Cell.Number(cell1.getNum.get)
  }
  else Cell.ErrorCell


def minus(cell1 : Cell, cell2:Cell) : Cell =
  if(isCellNum(cell1) && isCellNum(cell2)){
    Cell.Number(cell1.getNum.get - cell2.getNum.get)
  }
  else if (isCellEmpty(cell1) && isCellNum(cell2)){
    Cell.Number(cell2.getNum.get)
  }
  else if (isCellEmpty(cell2) && isCellNum(cell1)){
    Cell.Number(cell1.getNum.get)
  }
  else Cell.ErrorCell


def multiplication(cell1 : Cell, cell2:Cell) : Cell =
  if(isCellNum(cell1) && isCellNum(cell2)){
    // Je fais ça pour ne pas qu'on se retrouve avec des résultats du type -0
    if((cell1.getNum.get == 0) || (cell2.getNum.get == 0)){
      Cell.Number(0)
    }
    else Cell.Number(cell1.getNum.get * cell2.getNum.get)
  }
  else if (isCellEmpty(cell1) && isCellNum(cell2)){
    Cell.Number(cell2.getNum.get)
  }
  else if (isCellEmpty(cell2) && isCellNum(cell1)){
    Cell.Number(cell1.getNum.get)
  }
  else Cell.ErrorCell

def division(cell1 : Cell, cell2:Cell) : Cell =
  if(isCellNum(cell1) && isCellNum(cell2)){
    if((cell1.getNum.get == 0) && (cell2.getNum.get != 0)){
      Cell.Number(0)
    }
    else if(cell2.getNum.get == 0){
      Cell.ErrorCell
    }
    else Cell.Number(cell1.getNum.get / cell2.getNum.get)
  }
  else if (isCellEmpty(cell1) && isCellNum(cell2)){
    Cell.Number(cell2.getNum.get)
  }
  else if (isCellEmpty(cell2) && isCellNum(cell1)){
    Cell.Number(cell1.getNum.get)
  }
  else Cell.ErrorCell

def concat(cell1 : Cell, cell2:Cell) : Cell =
  if(isCellText(cell1) && isCellText(cell2)){
    Cell.Text(cell1.getText + " " + cell2.getText)
  }
  else if (isCellEmpty(cell1) && isCellText(cell2)){
    Cell.Text(cell2.getText)
  }
  else if (isCellEmpty(cell2) && isCellText(cell1)){
    Cell.Text(cell1.getText)
  }
  else Cell.ErrorCell

def minimum(listOfCell: List[Cell]): Cell =
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

def maximum(listOfCell: List[Cell]): Cell =
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

