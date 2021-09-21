package scala

class Test {

}

object Test {
  def main(args: Array[String]): Unit = {
    val a = List(1, 2, 3, 4)
    for (elem <- a) println(elem)
    System.out.println("hello world")
  }
}