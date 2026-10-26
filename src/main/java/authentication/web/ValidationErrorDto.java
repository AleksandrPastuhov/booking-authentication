package authentication.web;

import java.time.LocalDateTime;
import java.util.Map;

public record ValidationErrorDto(

        String message,

        Map<String, String> errors,

        LocalDateTime time
) {
}
