package pl.book.it.api.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Optional;

public class BookItException extends RuntimeException {
    private String fieldName;
    private Integer status;
    private String messages;

    public BookItException(Integer status, String message, String fieldName) {
        super(message);
        this.status = status;
        this.fieldName = fieldName;
    }

    public BookItException(Integer status, String message) {
        super(message);
        this.status = status;
    }

    public BookItException(String message, String fieldName) {
        super(message);
        this.fieldName = fieldName;
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

    public String getFieldName() {
        return fieldName;
    }

//    public Integer getName() {
//        return Optional.ofNullable(name).orElse(HttpStatus.BAD_REQUEST.value());
//    }
}
