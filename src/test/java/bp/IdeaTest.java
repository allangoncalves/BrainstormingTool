package bp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import bp.exceptions.BusinessException;
import bp.exceptions.StateException;

public class IdeaTest {

  public User owner;
  public Idea idea;
  public User randomUser;
  public Session session;

  @Before
  public void setUp() {
    this.owner = new User("Amano Kun");
    this.session = new Session(this.owner);
    this.idea = new Idea(this.owner, "breve descricao");
    this.idea.session = this.session;
    this.randomUser = new User("Participant");
  }

  @Test
  public void createIdeaTest() {
    //O autor da ideia deve ser o mesmo que foi passado na sua criação
    assertEquals(this.owner, this.idea.author);

    //A lista de ideias não pode ser nula
    assertNotNull(this.idea.voters);

    // A lista de votantes deve estar vazia
    assertEquals(0, this.idea.voters.size());
  }

  @Test(expected = BusinessException.class)
  public void notInSessionTest() throws BusinessException, StateException {
    //Os votos permanecem inalterados se o votante não estiver participando da sessão
    this.idea.session.phase = SessionPhase.VOTING;
    this.idea.registerVote(this.randomUser);
    assertEquals(0, this.idea.voters.size());
  }

  @Test(expected = StateException.class)
  public void notVotingPhaseTest() throws StateException, BusinessException {
    //Os votos permanecem inalterados se a sessão não estiver na fase de votação
    this.idea.session.phase = SessionPhase.BRAINSTORM;
    User user = new User("Zorome");
    this.session.addParticipant(user);
    this.idea.registerVote(user);
    assertEquals(0, this.idea.voters.size());
  }


  @Test(expected = BusinessException.class)
  public void voteFromOwnerTest() throws BusinessException, StateException {
    //Permanece alterada se o votante for o autor da ideia
    this.idea.session.phase = SessionPhase.VOTING;
    this.idea.registerVote(this.owner);
  }

  @Test(expected = BusinessException.class)
  public void votingLimitTest() throws StateException, BusinessException {
    //Os votos permanecem inalterados se o votante já tiver alcançado o limite de votos
    this.randomUser.votes = 3;
    this.idea.session.phase = SessionPhase.WELCOME;
    this.idea.session.addParticipant(this.randomUser);
    this.idea.session.phase = SessionPhase.VOTING;
    this.idea.registerVote(this.randomUser);
  }
  @Test(expected = BusinessException.class)
  public void removeVoteNotParticipantTest() throws BusinessException, StateException {
    //Os votos permanecem inalterados se quem quer remover o voto não esta na lista de participantes
    this.idea.session.phase = SessionPhase.VOTING;
    this.idea.reclaimVote(new User("Zero Two"));
  }

  @Test
  public void validVoteTest() throws StateException, BusinessException {
    //Voto válido
    this.idea.session.phase = SessionPhase.WELCOME;
    this.idea.session.addParticipant(this.randomUser);
    this.idea.session.phase = SessionPhase.VOTING;
    this.idea.registerVote(this.randomUser);
    assertEquals(1, this.idea.voters.size());
  }

  @Test(expected = StateException.class)
  public void NotVotingTest() throws BusinessException, StateException {
    //Os votos permanecem inalterados se a sessão não estiver na fase de votação
    this.idea.session.phase = SessionPhase.RANK;
    this.idea.reclaimVote(this.randomUser);
  }

  @Test
  public void reclaimVoteTest() throws BusinessException, StateException {
    //Quem quer remover o voto deixa de fazer parte da lista de votantes e o tamanho é decrementado
    this.idea.session.phase = SessionPhase.WELCOME;
    this.idea.session.addParticipant(this.randomUser);
    this.idea.session.phase = SessionPhase.VOTING;
    this.idea.registerVote(this.randomUser);
    this.idea.reclaimVote(this.randomUser);
    assertEquals(0, this.idea.voters.size());
  }

}
