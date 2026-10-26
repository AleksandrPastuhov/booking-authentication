package authentication.registration;

import authentication.domain.User;

public class UserResponseDto {

    private String username;

    public UserResponseDto(User user) {
        this.username = user.getUsername();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
