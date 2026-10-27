package authentication.registration;

import authentication.domain.User;
import authentication.domain.UserRepository;
import authentication.domain.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final Logger log = LoggerFactory.getLogger(RegistrationService.class);
    private final PasswordEncoder passwordEncoder;


    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User createUser(User createUser) {
        if(userRepository.existsByUsername(createUser.getUsername())) {
            throw new UserAlreadyExistsException(createUser.getUsername());
        }
        createUser.setUserRole(UserRole.User);
        createUser.setPassword(passwordEncoder.encode(createUser.getPassword()));
        userRepository.save(createUser);
        log.info("Create user:{}", createUser.getUsername());

        return createUser;
    }

}
