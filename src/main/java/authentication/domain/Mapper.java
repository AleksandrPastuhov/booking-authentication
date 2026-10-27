package authentication.domain;

import authentication.login.LoginResponseDto;
import authentication.registration.RegistrationRequestDto;
import authentication.registration.RegistrationResponseDto;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public User registrationRequestDtoToUser(RegistrationRequestDto registrationRequestDto) {
        return new User(registrationRequestDto.username(), registrationRequestDto.password());
    }

    public RegistrationResponseDto userToRegistrationResponseDto(User user) {
        return new RegistrationResponseDto(user);
    }

    public LoginResponseDto userToLoginResponseDto(User user) {
        return new LoginResponseDto(user.getUsername());
    }
}
