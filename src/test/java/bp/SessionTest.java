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
    //O proprietário deve ser o mesmo do que foi passado na sua construção
    assertEquals(this.user, this.session.owner);

    //Deve ser inicializada com a fase WELCOME
    assertEquals(SessionPhase.WELCOME, this.session.phase);

    //A lista não pode ser nula
    assertNotNull(this.session.ideas);

    //A lista não pode ser nula
    assertNotNull(this.session.participants);

    //A lista tem que estar vazia
    assertEquals(0, this.session.ideas.size());

    //A lista tem que estar vazia
    assertEquals(0, this.session.participants.size());
  }

  @Test
  public void nextPhase() {

    //Se tiver na fase de acolhimento, a sessão passa para a fase de Brainstorming
    this.session.phase = SessionPhase.WELCOME;
    assertEquals(this.session.nextPhase(), SessionPhase.BRAINSTORM);

    //Se tiver na fase de Brainstorming, a sessão passa para a fase de votação
    this.session.phase = SessionPhase.BRAINSTORM;
    assertEquals(this.session.nextPhase(), SessionPhase.VOTING);

    //Se tiver na fase de votação, a sessão passa para a fase de encerramento
    this.session.phase = SessionPhase.VOTING;
    assertEquals(this.session.nextPhase(), SessionPhase.RANK);

    //Se tiver na fase de encerramento, nada acontece
    this.session.phase = SessionPhase.RANK;
    assertEquals(this.session.nextPhase(), SessionPhase.RANK);
  }

  @Test
  public void newIdeaTest() {
    //A lista de ideias é inalterada se a sessão não estiver na fase de brainstorming
    this.session.phase = SessionPhase.WELCOME;
    this.session.addIdea(new Idea(this.user, "Description"));
    assertEquals(0, session.ideas.size());
    
    //A lista de ideias é inelterada se o autor da ideia não for um participante da sessao
    this.session.phase = SessionPhase.BRAINSTORM;
    this.session.addIdea(new Idea(new User(""), "Description"));
    assertEquals(0, this.session.ideas.size());

    //caso valido, a ideia é adicionada
    this.session.addIdea(new Idea(this.user, "Description"));
    assertEquals(1, this.session.ideas.size());
  }

  @Test
  public void addParticipantTest() {
    //A lista é inalterada se não estiver na fase de acolhimento
    this.session.phase = SessionPhase.BRAINSTORM;
    this.session.addParticipant(new User("Jane Doe"));
    assertEquals(1, this.session.participants.size());

    //A lista de participantes é inalterada se o mesmo usuário for adicionado
    this.session.addParticipant(this.user);
    assertEquals(1, this.session.participants.size());
    
    //Participante adicionado na fase de acolhimento deve ser aceito
    this.session.phase = SessionPhase.WELCOME;
    this.session.addParticipant(new User("Jane Doe"));
    assertEquals(2, this.session.participants.size());
    
  }

  @Test
  public void removeParticipantTest() {
    //A lista de participantes é inalterada se o usuário não é um participante
    this.session.removeParticipant(new User(""));
    assertEquals(1, this.session.participants.size());

    //Se o usuário for um participante, ele deve ser removido da lista de participantes
    this.session.removeParticipant(this.user);
    assertEquals(0, this.session.participants.size());
  }

}