package authentication.login;

import authentication.domain.User;
import authentication.domain.UserRepository;
import authentication.registration.RegistrationService;
import authentication.registration.UserAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private final UserRepository userRepository;
    private final Logger log = LoggerFactory.getLogger(RegistrationService.class);
    private final PasswordEncoder passwordEncoder;

    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User login(String login, String password) {
        Optional<User> user = userRepository.findByUsername(login);
        if(user.isEmpty()) {
            throw new InvalidCredentialsException();
        }
        if(!passwordEncoder.matches(password, user.get().getPassword())) {
            throw new InvalidCredentialsException();
        }
        return new User(login, password);
    }
}
