package ma.emsi.blog.exception;

public class DuplicateUserException extends RuntimeException {
	public DuplicateUserException(String message) {
		super(message);
	}
}