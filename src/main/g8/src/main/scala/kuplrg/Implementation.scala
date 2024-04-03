package kuplrg

object Implementation extends Template {

  import Fuzzer.*

  /** This is the playground for you to run your implementation. Do whatever you
    * want here and run `sbt run` to see the result.
    */
  @main def playground: Unit = {
    println("------------------- PLAYGROUND -------------------")

    // example DFA
    val dfa1 = DFA(1, "01", "0", "1")
    val dfa2 = DFA(2, "01", "3", "2")
    val dfa3 = DFA(2, "ab", "5", "2")

    // You can dump an FA to see it in the automaton viewer.
    // After running this program, open `viewer/index.html` in your browser.
    dfa1.dump

    println("--------------------------------------------------")

    // You can generate random FA or RE to test your implementation.
    fuzzDFA(n = 3, symbols = "01").dump

    println("--------------------------------------------------")
  }

  // ---------------------------------------------------------------------------
  // Problem 1: Table-Filling Algorithm
  // ---------------------------------------------------------------------------
  /** Calculate the set of pairs of non-equivalent states.
    *
    * @param dfa
    *   a DFA
    * @return
    *   the set of pairs of non-equivalent states
    */
  def nonEqPairs(dfa: DFA): Set[(State, State)] = ???

  // ---------------------------------------------------------------------------
  // Problem 2: Equivalence of DFA
  // ---------------------------------------------------------------------------
  /** Test the equivalence between the DFA and another automaton.
    *
    * @param ldfa
    *   a given DFA
    * @param rdfa
    *   a given DFA
    * @return
    *   true if the two DFAs are equivalent; otherwise, false
    */
  def isEqual(ldfa: DFA, rdfa: DFA): Boolean = ???

  // ---------------------------------------------------------------------------
  // Problem 3: Minimization of DFA
  // ---------------------------------------------------------------------------
  /** Minimize the DFA.
    *
    * @param dfa
    *   a given DFA
    * @return
    *   the minimized DFA
    */
  def minimize(dfa: DFA): DFA =
    val DFA(states, symbols, trans, initState, finalStates) = dfa
    val nonEqMap: Map[State, Set[State]] = nonEqPairs(dfa).foldLeft(
      Map[State, Set[State]]().withDefaultValue(Set()),
    ) { case (m, (p, q)) => m + (p -> (m(p) + q)) }
    def aux(qs: List[State], visited: Set[State]): Set[State] = qs match
      case Nil => visited
      case q :: rest =>
        aux(
          (symbols.map(trans(q, _)) -- visited - q).toList.sorted ++ rest,
          visited + q,
        )
    val reachable = aux(List(initState), Set())
    ???
}
