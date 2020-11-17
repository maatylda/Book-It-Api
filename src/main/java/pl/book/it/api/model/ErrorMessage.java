package pl.book.it.api.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Data
//zmienić nazwę klasy
public class ErrorMessage {

    private Integer status;
    private Map<String, String> errors;

    public ErrorMessage(Integer status, Map<String, String> errors ) {
        this.errors = errors;
        this.status = status;
    }

    public ErrorMessage() {
        this.errors = new HashMap<>();
    }
    public Integer getStatus() {
        return Optional.ofNullable(status).orElse(HttpStatus.BAD_REQUEST.value());
    }
}

