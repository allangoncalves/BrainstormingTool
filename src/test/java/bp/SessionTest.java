package bp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

/**
 * SessionTest
 */

public class SessionTest {
	
	public Session session;
	public User user;
	
	@Before
	public void setUp() {
		this.user = new User("John Doe");
		this.session = new Session(this.user);
		
	}
	
	@Test
	public void constructorTest() {
		assertEquals(this.user, this.session.owner);
		assertEquals(SessionPhase.WELCOME, this.session.phase);
		assertNotNull(this.session.ideas);
		assertNotNull(this.session.participants);
		assertEquals(0, this.session.ideas.size());
		assertEquals(0, this.session.participants.size());
	}
	
	@Test
	public void nextPhase() {
		this.session.phase = SessionPhase.WELCOME;
		assertEquals(this.session.nextPhase(), SessionPhase.BRAINSTORM);
		
		this.session.phase = SessionPhase.BRAINSTORM;
		assertEquals(this.session.nextPhase(), SessionPhase.VOTING);
		
		this.session.phase = SessionPhase.VOTING;
		assertEquals(this.session.nextPhase(), SessionPhase.RANK);
		
		this.session.phase = SessionPhase.RANK;
		assertEquals(this.session.nextPhase(), SessionPhase.RANK);
	}
	 
	@Test
	public void newIdeaTest() {
		this.session.phase = SessionPhase.WELCOME;
		this.session.addIdea(new Idea(this.user, "Description"));
		assertEquals(0, session.ideas.size());
		
		this.session.phase = SessionPhase.BRAINSTORM;
		this.session.addIdea(new Idea(new User(""), "Description"));
		assertEquals(0, this.session.ideas.size());
		
		this.session.addIdea(new Idea(this.user, "Description"));
		assertEquals(1, this.session.ideas.size());
	}
	
	@Test
	public void addIdeaTest() {
		this.session.phase = SessionPhase.BRAINSTORM;
		this.session.addParticipant(this.user);
		this.session.addIdea(new Idea(this.user, "Description"));
		assertEquals(1, this.session.ideas.size());
	}
	
	@Test
	public void addParticipantTest() {
		this.session.phase = SessionPhase.BRAINSTORM;
		this.session.addParticipant(new User("Cicrano"));
		assertEquals(1, this.session.participants.size());
		
		this.session.addParticipant(this.user);
		assertEquals(1, this.session.participants.size());
	}
	
	@Test
	public void removeParticipantTest() {
		this.session.removeParticipant(new User(""));
		assertEquals(1, this.session.participants.size());
		
		this.session.removeParticipant(this.user);
		assertEquals(0, this.session.participants.size());
	}

}