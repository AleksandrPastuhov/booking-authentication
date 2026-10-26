package authentication.registration;

import authentication.domain.User;
import authentication.domain.UserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    UserService userService;

    @Mock
    Mapper mapper;

    @InjectMocks
    UserController userController;

    @Test
    void defaultTest() {
        UserRequestDto userRequestDto = new UserRequestDto("alice", "1234");
        User user = new User("alice", "1234", UserRole.User);
        UserResponseDto userResponseDto= new UserResponseDto(user);
        User savedUser = new User("alice", "1234", UserRole.User);
        when(userService.createUser(user))
                .thenReturn(savedUser);
        when(mapper.userRequestDtoToUser(userRequestDto))
                .thenReturn(user);
        when(mapper.userToUserResponseDto(savedUser))
                .thenReturn(userResponseDto);
        ResponseEntity<UserResponseDto> responseEntity = userController.createUser(userRequestDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(userResponseDto, responseEntity.getBody());

        verify(mapper).userRequestDtoToUser(userRequestDto);
        verify(mapper).userToUserResponseDto(savedUser);
        verify(userService).createUser(user);
    }

    @Test
    void usernameBusyTest() {
        UserRequestDto userRequestDto = new UserRequestDto("alice", "1234");
        User user = new User("alice", "1234", UserRole.User);
        UserResponseDto userResponseDto= new UserResponseDto(user);
        User savedUser = new User("alice", "1234", UserRole.User);
        when(userService.createUser(user))
                .thenThrow(new UserAlreadyExistsException(user.getUsername()));
        when(mapper.userRequestDtoToUser(userRequestDto))
                .thenReturn(user);
        ResponseEntity<UserResponseDto> responseEntity = userController.createUser(userRequestDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(savedUser, responseEntity.getBody());
    }
}


