package pl.book.it.api.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.book.it.api.annotations.HandledByBookItExceptionHandler;
import pl.book.it.api.exceptions.BookItException;
import pl.book.it.api.model.ErrorMessage;

import java.util.HashMap;
import java.util.List;

@RestControllerAdvice(annotations = {HandledByBookItExceptionHandler.class})
@Slf4j
public class GlobalExceptionHandler {

    public GlobalExceptionHandler() {
    }

    @ExceptionHandler(BookItException.class)
    public ResponseEntity<ErrorMessage> handleBookItException(final BookItException exp) {
        final ErrorMessage errorMessage = new ErrorMessage(exp.getStatus(), exp.getMessage(), exp.getCode());
        return ResponseEntity.status(exp.getStatus()).body(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(final MethodArgumentNotValidException exp) {
        final HashMap<String, String> errorMessagesByFieldName = new HashMap<>();

        final List<FieldError> fieldErrors = exp.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            errorMessagesByFieldName.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(errorMessagesByFieldName));
    }

}