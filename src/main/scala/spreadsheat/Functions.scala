package spreadsheat

import spreadsheat.Cell.isCellNum
import spreadsheat.Cell.isCellEmpty
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
  else if (isCellEmpty(cell1)){
    Cell.Number(cell2.getNum.get)
  }
  else if (isCellEmpty(cell2)){
    Cell.Number(cell1.getNum.get)
  }
  else Cell.ErrorCell

/*
  def moins(l: List[Int]): Int = l.head + l.tail.foldLeft(0)(_ - _)

  def multiplier(l: List[Int]): Int = l.head * l.tail.foldLeft(1)(_ * _)

  def diviser(l: List[Double]): Double = l.head * l.tail.foldLeft(1.0)((acc, curr) => acc / curr)

  def concat(v1: String, v2: String): String = v1 + v2

  def minimum(mini: List[Int]): Int =
  {
    mini.min
  }

  def maximum(maxi: List[Int]): Int =
    maxi.max

*/

