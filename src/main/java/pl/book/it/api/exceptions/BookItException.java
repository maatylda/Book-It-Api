package pl.book.it.api.exceptions;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public class BookItException extends RuntimeException {
    private Integer code;
    private Integer status;
    private List<String> messages;

    public BookItException(Integer status, String message, Integer code) {
        super(message);
        this.status = status;
        this.code = code;
    }

    public BookItException(Integer status, String message) {
        super(message);
        this.status = status;
    }

    public BookItException(String message, Integer code) {
        super(message);
        this.code = code;
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

    public Integer getCode() {
        return Optional.ofNullable(code).orElse(HttpStatus.BAD_REQUEST.value());
    }
}
