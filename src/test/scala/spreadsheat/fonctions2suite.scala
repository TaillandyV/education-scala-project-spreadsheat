package spreadsheat
import munit.Clue.generate
import spreadsheat.Cell
import math.Ordered.orderingToOrdered
import math.Ordering.Implicits.infixOrderingOps
import munit.Clue.generate
import spreadsheat.Cell.stringToBooleen

def equals(l1 : Cell, l2: Cell): Boolean = l1.equals(l2)


def inf(l1 : Cell.Number , l2: Cell.Number ): Boolean = {
  val a1= println(Cell.stringToCell(l1))
  val a2= println(Cell.stringToCell(l2))
  def inf(a1 : Int , a2: Int ): Boolean = a1 < a2
}



//def < (v1 : ) : Boolean = (this compare that) < this

class Functions2Suite extends munit.FunSuite {

  test("Equal") {
    val s1 = Cell.Number(124)
    val s2 = Cell.Number(124)
    val result = s1.equals(s2)
    println(result)
    assertEquals(result, true)
  }

  test("inf") {
    val s1 = Cell.Number(124)
    val s2 = Cell.Number(124)
    val result = inf(s1,s2)
    println(result)
    assertEquals(result, true)
  }


}


