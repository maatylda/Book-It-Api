package pl.book.it.api.web;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.book.it.api.annotations.HandledByBookItExceptionHandler;
import pl.book.it.api.exceptions.BookItException;
import pl.book.it.api.model.ErrorMessage;

import java.util.List;

@RestControllerAdvice(annotations = {HandledByBookItExceptionHandler.class})
public class BIAExceptionHandler {

    @ExceptionHandler(BookItException.class)
    public ResponseEntity<ErrorMessage> handleBookItException(final BookItException exp) {
        final ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.getErrors().put(exp.getFieldName(), exp.getMessage());
        return ResponseEntity.status(exp.getStatus()).body(errorMessage);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(final MethodArgumentNotValidException exp) {
        final ErrorMessage errorMessage = new ErrorMessage();
        final List<FieldError> fieldErrors = exp.getBindingResult().getFieldErrors();
        fieldErrors.forEach(fieldError -> errorMessage.getErrors().put(fieldError.getField(), fieldError.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
