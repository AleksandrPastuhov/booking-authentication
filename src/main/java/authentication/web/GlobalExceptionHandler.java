package authentication.web;

import authentication.registration.UserAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageDto> handleEntityException(
            Exception e
    ) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorMessageDto(
                        "Exception",
                        LocalDateTime.now()
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDto> handleEntityMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ) {
        log.info("User specified not valid username or password");
        Map<String, String> errors = e.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                                FieldError::getField,
                                FieldError::getDefaultMessage,
                                (first, second) -> first + " and " + second)
                );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ValidationErrorDto(
                        "Not valid username or password",
                        errors,
                        LocalDateTime.now()
                ));
    }

    @ExceptionHandler({
            UserAlreadyExistsException.class,
            DataIntegrityViolationException.class
    })
    public ResponseEntity<ErrorMessageDto> handleEntityUserAlreadyExistsException(
            Exception e
    ) {
        log.info("username occupied");
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorMessageDto(
                        "This username occupied",
                        LocalDateTime.now()
                ));
    }
}
