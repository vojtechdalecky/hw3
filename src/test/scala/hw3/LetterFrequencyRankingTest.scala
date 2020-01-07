package hw3

import hw3.Main.letterFrequencyRanking
import org.scalatest.{FunSuite, Matchers}

class LetterFrequencyRankingTest extends FunSuite with Matchers {
  test("One source")      { letterFrequencyRanking(List("hello world")) shouldBe "lodehrw" }
  test("Two sources")     { letterFrequencyRanking(List("black tea", "dark tea", "green tea", "oolong tea", "white tea", "puer tea", "yellow tea")) shouldBe "eatlorgknwbcdhipuy" }
  test("Capital letters") { letterFrequencyRanking(List("AaaAaaAaa")) shouldBe "a" }
  test("Punctuation")     { letterFrequencyRanking(List("Sic!")) shouldBe "cis" }
}