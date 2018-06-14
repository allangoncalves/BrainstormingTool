package bp;

import java.util.ArrayList;
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
    this.session = new Session(author);
    this.author = author;
    this.description = description;
    this.voters = new ArrayList<>();
  }

  public void registerVote(User u) {
    //TODO registrar voto
  }

  public void reclaimVote(User u) {
    //TODO remover voto
  }

  public int countVotes() {
    //TODO contar votos
    return 0;
  }
  
}