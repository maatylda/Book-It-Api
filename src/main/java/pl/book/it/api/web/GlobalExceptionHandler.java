package pl.book.it.api.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.book.it.api.annotations.HandledByBookItExceptionHandler;
import pl.book.it.api.exceptions.BookItException;
import pl.book.it.api.model.ErrorMessage;

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


}
