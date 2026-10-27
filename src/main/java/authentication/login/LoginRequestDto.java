package authentication.login;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(

        @NotBlank(message = "login empty")
        String username,

        String password
) {
}
