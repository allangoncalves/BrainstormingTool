package bp.exceptions;
/*
 * Exceção que sinaliza erros relacionados a fase da sessão.
 */
public class StateException extends Exception {

  private static final long serialVersionUID = 1L;

  public StateException(String message) {
    super(message);
  }

}
