package kuplrg

trait Template {
  def nonEqPairs(dfa: DFA): Set[(State, State)]
  def isEqual(ldfa: DFA, rdfa: DFA): Boolean
  def minimize(dfa: DFA): DFA
}
