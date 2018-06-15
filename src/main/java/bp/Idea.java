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

	public void registerVote(User user) throws Exception {
		if(this.session.getPhase() == SessionPhase.VOTING) {
			if(this.author == user) {
				throw new Exception("Ideia não pode ser votada pelo seu dono!");
			}
			else if(!this.session.participants.contains(user)) {
				throw new Exception("Votante não é um participante");
			}
			else {
				this.voters.add(user);
			}
		}
		
	}

	public void reclaimVote(User user) throws Exception {
		if(!this.session.participants.contains(user)) {
			throw new Exception("Você não é um participante da sessão!");
		}
		else if(!this.voters.contains(user)) {
			throw new Exception("Você não havia votado na ideia!");
		}
		else {
			
		}

	}

	public int countVotes() {
		return this.voters.size();
	}

}