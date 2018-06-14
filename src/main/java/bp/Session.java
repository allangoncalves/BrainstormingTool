package bp;

import java.util.ArrayList;
import java.util.List;

/**
 * Session
 */
public class Session {

  User owner;
  SessionPhase phase;
  static final int VOTINGLIMIT = 3;
  List<Idea> ideas;
  List<User> participants;

  public Session(User owner) {
    this.owner = owner;
    this.phase = SessionPhase.WELCOME;
    this.ideas = new ArrayList<>();
    this.participants = new ArrayList<>();
  }

  public SessionPhase nextPhase() {
    return this.phase.next();
  }

  public SessionPhase getPhase() {
    return this.phase;
  }
  
  public boolean addIdea(Idea idea) {
    //TODO adicionar ideia
    return false;
  }

  public List<Idea> getIdeas() {
    return this.ideas;
  }

  public List<Idea> rankIdeas() {
    //TODO rankear ideias
    return null;
  }

  public void addParticipant(User participant) throws Exception {
    if(this.phase == SessionPhase.WELCOME) {
      this.participants.add(participant);
    }
    else {
      //TODO mudar a exceção
      throw new Exception("Não esta na fase de acolhimento.");
    }
  }

  public void removeParticipant(User participant) throws Exception {
      if(this.participants.contains(participant)) {
        this.participants.remove(participant);
      }
      else {
        //TODO alterar exceção
        throw new Exception("Participante não esta na lista de participantes.");
      }
  }

  public List<User> getParticipants() {
    return this.participants;
  }

}