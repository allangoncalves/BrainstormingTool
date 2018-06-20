package bp;

import java.util.ArrayList;
import java.util.List;
import bp.exceptions.BusinessException;
import bp.exceptions.StateException;

/**
 * Classe que representa uma ideia
 * @author allan
 *
 */
public class Idea {
  Session session;
  String description;
  User author;
  List<User> voters;
  /**
   * Construtor da classe Ideia
   * @param author
   * @param description
   */
  public Idea(User author, String description) {
    this.session = new Session(author);
    this.author = author;
    this.description = description;
    this.voters = new ArrayList<>();
  }
  /**
   * Registra o voto de um usuário caso ele seja um participante da sessão
   * @param user
   * @throws BusinessException
   * @throws StateException
   */
  public void registerVote(User user) throws BusinessException, StateException {
    if (this.session.getPhase() == SessionPhase.VOTING) {

      if (this.author == user) {
        throw new BusinessException("Ideia não pode ser votada pelo seu dono!");
      } else if (!this.session.participants.contains(user)) {
        throw new BusinessException("Votante não é um participante");
      } else if (this.voters.contains(user)) {
        throw new BusinessException("Usuário já votou na ideia!");
      } else if (user.votes == Session.VOTINGLIMIT) {
        throw new BusinessException("Usuário excedeu o limite de votos!");
      } else {
        this.voters.add(user);
      }
    } else {
      throw new StateException("Não é possível registrar voto fora da fase de votação");
    }

  }

  /**
   * Remove o voto de um usuário caso encontre um voto do usuário.
   * @param user
   * @throws BusinessException
   * @throws StateException
   */
  public void reclaimVote(User user) throws BusinessException, StateException {
    if (this.session.getPhase() == SessionPhase.VOTING) {
      if (!this.session.participants.contains(user)) {
        throw new BusinessException("Você não é um participante da sessão!");
      } else if (!this.voters.contains(user)) {
        throw new BusinessException("Você não havia votado na ideia!");
      } else {
        this.voters.remove(user);
      }
    } else {
      throw new StateException("Não é possível retirar voto fora da fase de votação ");
    }
  }

  public int countVotes() {
    return this.voters.size();
  }

}