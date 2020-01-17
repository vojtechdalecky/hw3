package hw3

import scala.collection.mutable.ListBuffer
import scala.math.sqrt
import scala.math.pow
import util.control.Breaks._

object Main {
  def standardDeviation(vector: List[Double]): Double = {

    var vecSum: Double = 0.0

    vector.foreach( x => vecSum += x)

    val avg: Double = vecSum / vector.size

    var sum: Double = 0.0

    vector.foreach(x => sum += pow(x - avg, 2) )

    sqrt(sum/vector.size)

  }

  def letterFrequencyRanking(corpus: String): String = {

    val lcCorpus = corpus.toLowerCase().replaceAll("[^a-z]","")

    val map = scala.collection.mutable.Map[Char, Int]()

    lcCorpus.foreach(x => {
      if(map.contains(x)) map(x) = map(x) + 1
      else map(x) = 1
    })

    var result: String = ""

    map.toSeq.sortBy(l => (-l._2, l._1)).foreach(c => {
      result += c._1
    })

    result

  }

  def romanji(katakana: String): String = {

    var romanji:String = ""

    var it:Int = 0
    for (c: Char <- katakana.toCharArray){
      breakable {
        if (Katakana.symbols.contains(c)) {
          Katakana.symbols(c).foreach(t => romanji += t)
          break
        }

        c match {
          case 'ャ' => {
            romanji = romanji.substring(0,romanji.length -1) + "ya"
            break
          }
          case 'ュ' => {
            romanji = romanji.substring(0,romanji.length -1) + "yu"
            break
          }
          case 'ョ' => {
            romanji = romanji.substring(0,romanji.length -1) + "yo"
            break
          }
          case default =>
        }

        if (c == 'ッ') {
          romanji += Katakana.symbols(katakana(it + 1)).head
          break
        }

        if (c == 'ン') {
          romanji += Katakana.symbols(katakana(it + 1)).head
          break
        }

        if (c == 'ー') {
          val lc: Char = romanji.takeRight(1).toCharArray.head
          romanji = romanji.substring(0,romanji.length -1)
          romanji += Katakana.longVowels(lc)
          break()
        }
      }

      it += 1
    }

  romanji
  }

  def gray(bits: Int): List[String] = {

    var combinations: List[String] = List("0", "1")

    if(bits != 1){
      val org = gray(bits-1)
      val rev = org.reverse
      combinations = org.map("0" + _) ::: rev.map("1" + _)
    }

    combinations

  }

  def infixToRPN(expression: List[Lexeme]): List[Lexeme] = {

    val operators: ListBuffer[Lexeme] = new ListBuffer[Lexeme]()
    val result: ListBuffer[Lexeme] = new ListBuffer[Lexeme]()
    var operatorSwitch: Boolean = true

    expression.foreach(x => {

     val opType: String = x.getClass.getSimpleName

     if(opType == "Operand"){
       operatorSwitch = false
       result += x
     }
     else {

         if(operatorSwitch) throw new IllegalArgumentException
         operatorSwitch = true

         if (operators.nonEmpty) {

           breakable {
             while (operators.nonEmpty) {

               if (((getOperatorInfo(x)._2 == "L") && (getOperatorInfo(x)._1 <= getOperatorInfo(operators.last)._1)) ||
                 ((getOperatorInfo(x)._2 == "R") && (getOperatorInfo(x)._1 < getOperatorInfo(operators.last)._1))) {
                 result += operators.last
                 operators.remove(operators.length - 1)
               } else break()
             }
           }
         }
         operators += x

     }

    })

    if(operatorSwitch && expression.nonEmpty) throw new IllegalArgumentException

  operators.reverse.foreach(x => result += x)

  //printResult(result)

  result.toList
  }

  def printResult(result: ListBuffer[Lexeme]): Unit = {
    result.foreach(x=>{

      if(x.getClass.getSimpleName == "Operator"){
        val Operator(operatorVal) = x
        print(operatorVal)
      }else{
        val Operand(operandVal) = x
        print(operandVal)
      }

    })

    println()
  }

  def getOperatorInfo(operator: Lexeme): (Int, String) ={

    val Operator(x) = operator

    x match {
     case "^" => (4,"R")
     case "*" => (3, "L")
     case "/" => (3, "L")
     case "+" => (2, "L")
     case "-" => (2, "L")
     case default => throw new IllegalArgumentException
    }
  }

  abstract class Lexeme

  case class Operator(symbol: String) extends Lexeme

  case class Operand(value: Int) extends Lexeme


}

object Katakana {
  val symbols = Map(
    'ア' -> List('a'), 'イ' -> List('i'),  'ウ' -> List('u'), 'エ' -> List('e'), 'オ' -> List('o'),
    'ン' -> List('n'),
    'カ' -> List('k','a'), 'キ' -> List('k','i'), 'ク' -> List('k','u'), 'ケ' -> List('k','e'), 'コ' -> List('k','o'),
    'ガ' -> List('g','a'), 'ギ' -> List('g','i'), 'グ' -> List('g','u'), 'ゲ' -> List('g','e'), 'ゴ' -> List('g','o'),
    'サ' -> List('s','a'), 'シ' -> List('s','i'), 'ス' -> List('s','u'), 'セ' -> List('s','e'), 'ソ' -> List('s','o'),
    'ザ' -> List('z','a'), 'ジ' -> List('z','i'), 'ズ' -> List('z','u'), 'ゼ' -> List('z','e'), 'ゾ' -> List('z','o'),
    'タ' -> List('t','a'), 'チ' -> List('t','i'), 'ツ' -> List('t','u'), 'テ' -> List('t','e'), 'ト' -> List('t','o'),
    'ダ' -> List('d','a'), 'ヂ' -> List('d','i'), 'ヅ' -> List('d','u'), 'デ' -> List('d','e'), 'ド' -> List('d','o'),
    'ナ' -> List('n','a'), 'ニ' -> List('n','i'), 'ヌ' -> List('n','u'), 'ネ' -> List('n','e'), 'ノ' -> List('n','o'),
    'ハ' -> List('h','a'), 'ヒ' -> List('h','i'), 'フ' -> List('h','u'), 'ヘ' -> List('h','e'), 'ホ' -> List('h','o'),
    'バ' -> List('b','a'), 'ビ' -> List('b','i'), 'ブ' -> List('b','u'), 'ベ' -> List('b','e'), 'ボ' -> List('b','o'),
    'パ' -> List('p','a'), 'ピ' -> List('p','i'), 'プ' -> List('p','u'), 'ペ' -> List('p','e'), 'ポ' -> List('p','o'),
    'マ' -> List('m','a'), 'ミ' -> List('m','i'), 'ム' -> List('m','u'), 'メ' -> List('m','e'), 'モ' -> List('m','o'),
    'ヤ' -> List('y','a'),                        'ユ' -> List('y','u'),                        'ヨ' -> List('y','o'),
    'ラ' -> List('r','a'), 'リ' -> List('r','i'), 'ル' -> List('r','u'), 'レ' -> List('r','e'), 'ロ' -> List('r','o'),
    'ワ' -> List('w','a'), 'ヰ' -> List('w','i'),                        'ヱ' -> List('w','e'), 'ヲ' -> List('w','o'),
  )
  val longVowels = Map(
    'a' -> 'ā',
    'i' -> 'ī',
    'e' -> 'ē',
    'u' -> 'ū',
    'o' -> 'ō'
  )
}