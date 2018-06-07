package bp;

import java.util.List;

/**
 * Idea
 */
public class Idea {
  Session session;
  String description;
  User author;
  List<User> voters;

  public Idea(User author, String description) {
  }

  public void registerVote(User u) {
  }

  public void reclaimVote(User u) {
  }

  public int countVotes() {
    return 0;
  }
  
}