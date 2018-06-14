package bp;

public enum SessionPhase {
  WELCOME,    // welcoming new participants
  BRAINSTORM, // creating new ideas
  VOTING,     // voting on the ideas
  RANK;       // final report
  
   SessionPhase next() {
    return this.ordinal() == 3 ? this: SessionPhase.values()[this.ordinal() + 1];
   }
}