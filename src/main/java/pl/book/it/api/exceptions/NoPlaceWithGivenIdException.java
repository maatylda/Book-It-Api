package pl.book.it.api.exceptions;

public class NoPlaceWithGivenIdException extends BookItException {
    public NoPlaceWithGivenIdException(String message) {
        super(message);
    }
}
