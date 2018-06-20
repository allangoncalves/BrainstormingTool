package bp;
/**
 * Diferentes fases de uma sessão
 * @author allan
 *
 */
public enum SessionPhase {
  WELCOME,    // welcoming new participants
  BRAINSTORM, // creating new ideas
  VOTING,     // voting on the ideas
  RANK;       // final report
  /**
   * Retorna a próxima fase
   * @return A próxima fase ou ela mesma se ja for a ultima.
   */
  SessionPhase next() {
    return this.ordinal() == 3 ? this: SessionPhase.values()[this.ordinal() + 1];
  }
}