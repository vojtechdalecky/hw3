package hw3

import org.scalatest.FunSuite
import Main.Lexeme
import Main.Operator
import Main.Operand
import Main.infixToRPN

class InfixToRPNTest extends FunSuite {

  test("2 + 2") {  
    assert(infixToRPN(List(Operand(2), Operator("+"), Operand(2))) === 
                      List(Operand(2), Operand(2), Operator("+")))
  }

  test("3 - 4") {
    assert(infixToRPN(List(Operand(3), Operator("-"), Operand(4))) === 
                      List(Operand(3), Operand(4), Operator("-")))
  }

  test("2 + 3 - 4") {
    assert(infixToRPN(List(Operand(2), Operator("+"), Operand(3), Operator("-"), Operand(4))) === 
                      List(Operand(2), Operand(3), Operator("+"), Operand(4), Operator("-")))
  }

  test("Illegal operator") {
    try{
      infixToRPN(List(Operand(2), Operator("-"), Operand(3), Operator("#"), Operand(4)))
      assert(false)
    }catch{
      case x: IllegalArgumentException => assert(true)
    }
  }

  test("Sequence of two operators next to each other") {
    try{
      infixToRPN(List(Operand(2), Operator("+"), Operator("+"), Operand(3), Operator("+"), Operand(4)))
      assert(false)
    }catch{
      case x: IllegalArgumentException => assert(true)
    }
  }

  test("Sequence starting with operator") {
    try{
      infixToRPN(List(Operator("-"),Operand(2), Operator("+"), Operator("+"), Operand(3), Operator("+"), Operand(4)))
      assert(false)
    }catch{
      case x: IllegalArgumentException => assert(true)
    }
  }

  test("Sequence ending with operator") {
    try{
      infixToRPN(List(Operand(2), Operator("+"), Operator("+"), Operand(3), Operator("+"), Operand(4), Operator("-")))
      assert(false)
    }catch{
      case x: IllegalArgumentException => assert(true)
    }
  }

  test("Empty sequence"){
    assert(infixToRPN(List() ) === List())
  }

  test("2 + 3 - 4 * 5 ^ 8 - 9 / 7 + 4 - 0 ^ 6") {
    assert(infixToRPN(List(
      Operand(2),
      Operator("+"),
      Operand(3),
      Operator("-"),
      Operand(4),
      Operator("*"),
      Operand(5),
      Operator("^"),
      Operand(8),
      Operator("-"),
      Operand(9),
      Operator("/"),
      Operand(7),
      Operator("+"),
      Operand(4),
      Operator("-"),
      Operand(0),
      Operator("^"),
      Operand(6)
    )) ===
      List(
        Operand(2),
        Operand(3),
        Operator("+"),
        Operand(4),
        Operand(5),
        Operand(8),
        Operator("^"),
        Operator("*"),
        Operator("-"),
        Operand(9),
        Operand(7),
        Operator("/"),
        Operator("-"),
        Operand(4),
        Operator("+"),
        Operand(0),
        Operand(6),
        Operator("^"),
        Operator("-")
      ))
  }

}
