package authentication.registration;

import jakarta.validation.constraints.*;

public record RegistrationRequestDto(

        @NotBlank(message = "username must not be empty")
        @Size(min = 3, max = 30, message = "username can be min 3 and max 30 symbols")
        @Pattern(regexp = "^[0-9a-zA-Z_]+$", message = "wrong symbol in username")
         String username,

        @NotBlank(message = "password must not be empty")
        @Size(min = 8, max = 16, message = "password can be min 8 and max 16 symbols")
        String password
) {}
