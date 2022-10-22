package spreadsheat
import munit.Clue.generate
import spreadsheat.Cell



def equals(l1: List[String], l2: List[String]): Boolean = l1.equals(l2)


def compare(v1: String, v2: String) : Int = (v1).compare(v2)

class Functions2Suite extends munit.FunSuite {

  test("Equal") {
    val s1 = Set(11, 10, 2019)
    val s2 = Set(9, 10, 2018)
    val result = s1.equals(s2)
    assertEquals(result, false)
  }

  test("inf") {
    val v1 = 10
    val v2 = 10
    val result = (v1).compare(v2)
    assertEquals(result, 0)
  }


}
