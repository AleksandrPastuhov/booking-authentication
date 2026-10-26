package authentication.registration;

import jakarta.validation.constraints.*;

public class UserRequestDto {

    @NotBlank(message = "username must not be empty")
    @Size(min = 3, max = 30, message = "username can be min 3 and max 30 symbols")
    @Pattern(regexp = "^[0-9a-zA-Z]+$", message = "wrong symbol in username")
    private String username;

    @NotBlank(message = "password must not be empty")
    @Size(min = 8, max = 16, message = "password can be min 8 and max 16 symbols")
    private String password;

    public UserRequestDto(String user, String password) {
        username = user;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
