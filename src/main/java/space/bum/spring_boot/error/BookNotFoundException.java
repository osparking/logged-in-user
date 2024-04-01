package space.bum.spring_boot.error;

public class BookNotFoundException extends Exception {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public BookNotFoundException() {
    super();
  }

  public BookNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
