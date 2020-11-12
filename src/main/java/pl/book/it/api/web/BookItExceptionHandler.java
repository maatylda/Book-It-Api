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
public class BookItExceptionHandler {

    public BookItExceptionHandler() {
    }

    @ExceptionHandler(BookItException.class)
    public ResponseEntity<ErrorMessage> handleBookItException(final BookItException exp) {
        return ResponseEntity.status(exp.getStatus()).body(new ErrorMessage(exp.getStatus(), exp.getMessage(), exp.getCode()));
    }


}
