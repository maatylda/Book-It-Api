package pl.book.it.api.exceptions;

public class NoRoomsAvailable extends BookItException {
    public NoRoomsAvailable() {
    }

    public NoRoomsAvailable(String message) {
        super(message);
    }

    public NoRoomsAvailable(String message, Throwable cause) {
        super(message, cause);
    }

    public NoRoomsAvailable(Throwable cause) {
        super(cause);
    }
}
