package pl.book.it.api.web;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.book.it.api.annotations.HandledByBookItExceptionHandler;
import pl.book.it.api.exceptions.BookItException;
import pl.book.it.api.model.ErrorMessage;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@RestControllerAdvice(annotations = {HandledByBookItExceptionHandler.class})
@Slf4j
public class BookItExceptionHandler {

    private final Map<HttpMethod, Integer> methodToErrorCount = new HashMap<>();

    public BookItExceptionHandler() {
        Stream.of(HttpMethod.GET, HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE)
                .forEach(method -> methodToErrorCount.put(method, 0));
    }


    @ExceptionHandler(BookItException.class)
    public ResponseEntity<ErrorMessage> handleBookItException(final BookItException exp,
                                                              final HttpServletRequest request) {
        final String method = request.getMethod();
        final HttpMethod httpMethod = HttpMethod.resolve(method);
        methodToErrorCount.computeIfPresent(httpMethod, (m, count) -> ++count);
        final String requestedFailureUri = request.getRequestURI();
        return ResponseEntity.status(exp.getStatus()).body(new ErrorMessage(exp.getMessage(),
                List.of(requestedFailureUri, methodToErrorCount), exp.getCode()));
    }


}
