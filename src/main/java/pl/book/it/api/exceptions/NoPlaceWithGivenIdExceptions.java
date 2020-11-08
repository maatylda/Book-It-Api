package pl.book.it.api.exceptions;

import java.util.function.Supplier;

public class NoPlaceWithGivenIdExceptions extends BookItExceptions {
    public NoPlaceWithGivenIdExceptions(String message) {
        super(message);
    }
}
