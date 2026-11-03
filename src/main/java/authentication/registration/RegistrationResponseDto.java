package authentication.registration;

import authentication.domain.User;

public record RegistrationResponseDto(
        String login
) {

    public RegistrationResponseDto(User user) {
        this(user.getLogin());
    }
}
