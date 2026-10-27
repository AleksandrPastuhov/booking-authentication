package authentication.registration;

import authentication.domain.User;

public record RegistrationResponseDto(
        String username
) {

    public RegistrationResponseDto(User user) {
        this(user.getUsername());
    }
}
