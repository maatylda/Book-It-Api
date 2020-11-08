package pl.book.it.api.exceptions;

public class BookItExceptions extends RuntimeException{
    public BookItExceptions() {
    }

    public BookItExceptions(String message) {
        super(message);
    }

    public BookItExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public BookItExceptions(Throwable cause) {
        super(cause);
    }
}
