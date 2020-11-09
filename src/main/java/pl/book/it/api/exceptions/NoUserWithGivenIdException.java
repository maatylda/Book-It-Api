package pl.book.it.api.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Optional;

public class NoUserWithGivenIdException extends BookItException {
    private Integer status;

    public NoUserWithGivenIdException(String message) {
        super(message);

    }

    public NoUserWithGivenIdException(Integer status, String message) {
        super(status, message);
    }

    public NoUserWithGivenIdException() {
    }

    public Integer getStatus() {
        return Optional.ofNullable(status).orElse(HttpStatus.BAD_REQUEST.value());
    }
}
