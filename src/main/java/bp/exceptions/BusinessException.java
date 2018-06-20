package bp.exceptions;
/**
 * Exceção para sinalizar erros de quebra das regras de negócio.
 * @author allan
 *
 */
public class BusinessException extends Exception{

  private static final long serialVersionUID = -4566417186794078260L;

  public BusinessException(String message) {
    super(message);
  }
}

