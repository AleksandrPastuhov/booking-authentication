package authentication.registration;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String login) {
        super("User already exists:" + login);
    }
}
