package authentication.login;

import authentication.domain.Mapper;
import authentication.domain.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;
    private final Mapper mapper;

    public LoginController(LoginService loginService, Mapper mapper) {
        this.loginService = loginService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<LoginResponseDto> login(
            @RequestBody @Valid LoginRequestDto loginRequestDto
    ) {
        User user = loginService.login(loginRequestDto.username(), loginRequestDto.password());
        return ResponseEntity.ok(mapper.userToLoginResponseDto(user));
    }
}
