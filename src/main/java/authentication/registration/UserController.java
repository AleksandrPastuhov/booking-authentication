package authentication.registration;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/create")
public class UserController {

    private final UserService userService;

    private final Mapper mapper;

    public UserController(UserService userService, Mapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(
        @RequestBody @Valid UserRequestDto userRequestDto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.userToUserResponseDto(
                                userService.createUser(
                                                mapper.userRequestDtoToUser(userRequestDto))));
    }
}
