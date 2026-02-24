package tw.lab.Spring07.exception;

public class JwtAuthException extends RuntimeException {
    public JwtAuthException(String mesg) {
        super(mesg);
    }
}
