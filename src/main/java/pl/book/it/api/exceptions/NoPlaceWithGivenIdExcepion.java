package pl.book.it.api.exceptions;

import java.util.function.Supplier;

public class NoPlaceWithGivenIdExcepion extends RuntimeException  {
    public NoPlaceWithGivenIdExcepion() {
    }

    public NoPlaceWithGivenIdExcepion(String message) {
        super(message);
    }

    public NoPlaceWithGivenIdExcepion(String message, Throwable cause) {
        super(message, cause);
    }

    public NoPlaceWithGivenIdExcepion(Throwable cause) {
        super(cause);
    }

    public NoPlaceWithGivenIdExcepion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
