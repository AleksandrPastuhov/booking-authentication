package authentication.registration;

import authentication.domain.User;
import authentication.domain.UserRole;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public User userRequestDtoToUser(UserRequestDto userRequestDto) {
        return new User(userRequestDto.getUsername(), userRequestDto.getPassword());
    }

    public UserResponseDto userToUserResponseDto(User user) {
        return new UserResponseDto(user);
    }
}
