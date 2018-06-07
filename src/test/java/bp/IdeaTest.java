package bp;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class IdeaTest {
	
	public User owner;
	public Idea idea;
	public User participant;
	public Session session;
	
	@Before
	public void setUp() {
		this.owner = new User("Amano Kun");
		this.session = new Session(this.owner);
		this.idea = new Idea(this.owner, "breve descricao");
		this.idea.session = this.session;
		
	}
	
	@Test
	public void createIdeaTest() {
		assertEquals(this.owner, this.idea.author);
		assertEquals(0, this.idea.voters.size());
	}
	
	@Test
	public void ideaVoteTest() {
		this.idea.session.phase = SessionPhase.BRAINSTORM;
		this.idea.registerVote(new User("Zorome"));
		assertEquals(0, this.idea.voters.size());
		
		
		this.idea.session.phase = SessionPhase.VOTING;
		this.idea.registerVote(this.participant);
		assertEquals(0, this.idea.voters.size());
		
		this.idea.registerVote(this.owner);
		assertEquals(0, this.idea.voters.size());
		
		
		this.participant.votes = 10;
		this.idea.session.addParticipant(this.participant);
		this.idea.registerVote(this.participant);
		assertEquals(0, this.idea.voters.size());
		
	}
	
	@Test
	public void ideaUnvoteTest() {
		this.idea.session.phase = SessionPhase.VOTING;
		this.idea.session.addParticipant(this.participant);
		this.idea.registerVote(this.participant);
		this.idea.session.phase = SessionPhase.RANK;
		this.idea.reclaimVote(this.participant);
		assertEquals(1, this.idea.voters.size());
		
		this.idea.reclaimVote(new User("Zero Two"));
		assertEquals(1, this.idea.voters.size());
	}

}
