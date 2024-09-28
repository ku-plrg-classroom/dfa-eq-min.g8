package kuplrg

object Fuzzer {

  /** Random number generator. */
  val rand = new scala.util.Random

  /** Fuzzes a DFA with `n` states and `symbols` symbols. */
  def fuzzDFA(n: Int, symbols: String): DFA = DFA(
    (1 to n).toSet,
    symbols.toSet,
    (for {
      q <- (1 to n).toSet
      a <- symbols.toSet
    } yield (q, a) -> (randInt(n) + 1)).toMap,
    1,
    (1 to n).toSet.filter(_ => randBool),
  )

  /** Random integer set in the range `[from, to]` with density `density`. */
  def randIntSet(from: Int, to: Int, density: Double = 0.5): Set[Int] =
    (from to to).toSet.filter(_ => randBool(density))

  /** Random boolean. */
  def randBool: Boolean = rand.nextBoolean

  /** Random boolean with probability `probForTrue`. */
  def randBool(probForTrue: Double): Boolean = rand.nextDouble < probForTrue

  /** Random integer in the range `[0, bound)`. */
  def randInt(bound: Int): Int = rand.nextInt(bound)
}
