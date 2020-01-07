package hw3

object Main {
  def standardDeviation(vector: List[Double]): Double = ???

  def letterFrequencyRanking(corpus: List[String]): String = ???

  def romanji(katakana: String): String = ???

  case class Competitor(val name: String, val scoreInPreviousRound: Option[Int])
  def dutch(competitors: List[Competitor], groupSize: Int, randomSeed: Option[Int] = None): Set[(Competitor, Option[Competitor])] = ???
}