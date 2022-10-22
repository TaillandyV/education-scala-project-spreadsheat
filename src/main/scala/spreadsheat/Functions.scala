package spreadsheat

import spreadsheat.Cell.isCellNum
import spreadsheat.Cell.isCellEmpty


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
        //println("ErrorCell")
        Cell.ErrorCell
      }
  }
  Cell.Number(res)
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


