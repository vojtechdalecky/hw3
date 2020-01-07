package hw3

import org.scalatest.{FunSuite, Matchers}
import Main.dutch
import Main.Competitor

class DutchTest extends FunSuite with Matchers {

  val seed = 666

  val competitorsBlank = List (
    Competitor("Boleslaus I the Cruel", None),
    Competitor("Boleslaus II the Pious", None),
    Competitor("Boleslaus III the Red-haired", None),
    Competitor("Boleslaus IV", None)
  )

  val competitorsEven = List(
    Competitor("Bořivoj I", Some(6)),
    Competitor("Henry Bretislaus", Some(3)),
    Competitor("Vladislaus Henry", Some(5)),
    Competitor("Ottokar I ", Some(0)),
    Competitor("Wenceslaus I", Some(2)),
    Competitor("Ottokar II", Some(1)),
    Competitor("Wenceslaus II", Some(10)),
    Competitor("Wenceslaus III", Some(7))
  )

  val competitorsOdd = competitorsEven ::: List(
    Competitor("Svatopluk", Some(8)),
    Competitor("Sobeslaus I", Some(4)),
    Competitor("Spytihněv I", Some(9))
  )

  test("First round") {

    val pairing = dutch(competitors = competitorsBlank, groupSize = 4, randomSeed = Some(seed))

    pairing shouldBe Set(
      (Competitor("Boleslaus I the Cruel", None), Competitor("Boleslaus II the Pious", None)),
      (Competitor("Boleslaus IV", None), Competitor("Boleslaus III the Red-haired", None))
    )
  }

  test("Even competitors") {

    val pairing = dutch(competitors = competitorsEven, groupSize = 4)

    /*
     * group I:
     *   Wenceslaus II      10        Pair 1
     *   Wenceslaus III     7         Pair 2
     *   Bořivoj I          6         Pair 1
     *   Vladislaus Henry   5         Pair 2
     *
     * group II:
     *   Henry Bretislaus   3         Pair 3
     *   Wenceslaus I       2         Pair 4
     *   Ottokar II         1         Pair 3
     *   Ottokar I          0         Pair 4
     */
    pairing shouldBe Set(
      (Competitor("Wenceslaus II", Some(10)), Some(Competitor("Bořivoj I", Some(6)))),
      (Competitor("Wenceslaus III", Some(7)), Some(Competitor("Vladislaus Henry", Some(5)))),
      (Competitor("Henry Bretislaus", Some(3)), Some(Competitor("Ottokar II", Some(1)))),
      (Competitor("Wenceslaus I", Some(2)), Some(Competitor("Ottokar I ", Some(0))))
    )


    test("Odd competitors") {

      val pairing = dutch(competitors = competitorsOdd, groupSize = 4)

      /*
       * group I:
       *   Wenceslaus II      10        Pair 1
       *   Spytihněv          9         Pair 2
       *   Svatopluk          8         Pair 1
       *   Wenceslaus III     7         Pair 2
       *
       * group II:
       *   Bořivoj I          6         Pair 3
       *   Vladislaus Henry   5         Pair 4
       *   Sobeslaus I        4         Pair 3
       *   Henry Bretislaus   3         Pair 4
       *
       * group III:
       *   Wenceslaus I       2         Pair 5
       *   Ottokar II         1         Not paired
       *   Ottokar I          0         Pair 5
       */
      pairing shouldBe Set(
        (Competitor("Wenceslaus II", Some(10)), Some(Competitor("Svatopluk", Some(8))),
        (Competitor("Spytihněv I", Some(9)), Some(Competitor("Wenceslaus III", Some(7)))),
        (Competitor("Bořivoj I", Some(6))), Some(Competitor("Sobeslaus I", Some(4)))),
        (Competitor("Vladislaus Henry", Some(5)), Some(Competitor("Henry Bretislaus", Some(3)))),
        (Competitor("Wenceslaus I", Some(2)), Some(Competitor("Ottokar I ", Some(0)))),
        (Competitor("Ottokar II", Some(1)), None)
      )
    }
  }
}