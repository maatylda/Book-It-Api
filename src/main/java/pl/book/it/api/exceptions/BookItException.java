package pl.book.it.api.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Optional;

public class BookItException extends RuntimeException {

    private Integer status;

    public BookItException(Integer status, String message) {
        super(message);
        this.status = status;
    }

    public BookItException() {
    }

    public BookItException(String message) {
        super(message);
    }

    public BookItException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookItException(Throwable cause) {
        super(cause);
    }

    public Integer getStatus() {
        return Optional.ofNullable(status).orElse(HttpStatus.BAD_REQUEST.value());
    }
}
