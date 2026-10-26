package authentication.web;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ErrorMessageDto(

        String message,

        LocalDateTime time

){}
