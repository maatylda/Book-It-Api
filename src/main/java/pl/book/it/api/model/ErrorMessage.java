package pl.book.it.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    //zmienić nazwę klasy
    private Integer httpStatus;
    //private String message;
    private Integer code;
    private Map<String, String> errors;

    public ErrorMessage(Integer httpStatus, String message, Integer code) {
        this.httpStatus = httpStatus;
        //this.message = message;
        this.code = code;
    }

    public ErrorMessage(Map<String, String> errors) {
        this.errors = errors;
    }

}

