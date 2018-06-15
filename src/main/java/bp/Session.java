package bp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import bp.exceptions.BusinessException;
import bp.exceptions.StateException;

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
		this.participants.add(owner);
	}

	public SessionPhase nextPhase() {
		return this.phase.next();
	}

	public SessionPhase getPhase() {
		return this.phase;
	}

	public void addIdea(Idea idea) throws StateException, BusinessException {
		if(this.phase == SessionPhase.BRAINSTORM) {
		  if(this.participants.contains(idea.author)) {
		    this.ideas.add(idea);
		  }
		  else {
		    throw new BusinessException("Autor não participa da sessão!");
		  }
		}
		else {
			throw new StateException("Não está na fase de ideação!");
		}
	}

	public List<Idea> getIdeas() {
		return this.ideas;
	}

	public List<Idea> rankIdeas() throws StateException {
	  if(this.phase == SessionPhase.RANK) {
	    List<Idea> sortedIdeas = this.ideas;
        Collections.sort(sortedIdeas, Comparator.comparing(Idea::countVotes));
        return sortedIdeas;
	  }
	  else {
	    throw new StateException("Não é possível ranquear as ideias fora da fase de ranqueamento!");
	  }
		
	}

	public void addParticipant(User participant) throws StateException, BusinessException {
		if(this.phase == SessionPhase.WELCOME) {
		  if(!this.participants.contains(participant)) {
		    this.participants.add(participant);
		  }
		  else {
		    throw new BusinessException("Participante já faz parte da sessão!");
		  }
			
		}
		else {
			throw new StateException("Não esta na fase de acolhimento.");
		}
	}

	public void removeParticipant(User participant) throws BusinessException {
		if(this.participants.contains(participant)) {
			this.participants.remove(participant);
		}
		else {
			throw new BusinessException("Participante não esta na lista de participantes.");
		}
	}

	public List<User> getParticipants() {
		return this.participants;
	}

}