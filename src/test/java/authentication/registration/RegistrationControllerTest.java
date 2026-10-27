package authentication.registration;

import authentication.domain.Mapper;
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
public class RegistrationControllerTest {

    @Mock
    RegistrationService registrationService;

    @Mock
    Mapper mapper;

    @InjectMocks
    RegistrationController registrationController;

    @Test
    void defaultTest() {
        RegistrationRequestDto registrationRequestDto = new RegistrationRequestDto("alice", "1234");
        User user = new User("alice", "1234", UserRole.User);
        RegistrationResponseDto registrationResponseDto = new RegistrationResponseDto(user);
        User savedUser = new User("alice", "1234", UserRole.User);
        when(registrationService.createUser(user))
                .thenReturn(savedUser);
        when(mapper.registrationRequestDtoToUser(registrationRequestDto))
                .thenReturn(user);
        when(mapper.userToRegistrationResponseDto(savedUser))
                .thenReturn(registrationResponseDto);
        ResponseEntity<RegistrationResponseDto> responseEntity = registrationController.createUser(registrationRequestDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(registrationResponseDto, responseEntity.getBody());

        verify(mapper).registrationRequestDtoToUser(registrationRequestDto);
        verify(mapper).userToRegistrationResponseDto(savedUser);
        verify(registrationService).createUser(user);
    }

    @Test
    void usernameBusyTest() {
        RegistrationRequestDto registrationRequestDto = new RegistrationRequestDto("alice", "1234");
        User user = new User("alice", "1234", UserRole.User);
        RegistrationResponseDto registrationResponseDto = new RegistrationResponseDto(user);
        User savedUser = new User("alice", "1234", UserRole.User);
        when(registrationService.createUser(user))
                .thenThrow(new UserAlreadyExistsException(user.getUsername()));
        when(mapper.registrationRequestDtoToUser(registrationRequestDto))
                .thenReturn(user);
        ResponseEntity<RegistrationResponseDto> responseEntity = registrationController.createUser(registrationRequestDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(savedUser, responseEntity.getBody());
    }
}


