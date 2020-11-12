package pl.book.it.api.model;

public enum ApiErrors {
    USER_WITH_GIVEN_EMAIL_ALREADY_EXIST(101, "api.error.user.email.taken"),
    USER_NOT_FOUND(102, "api.error.user.not.found"),
    ROOM_NOT_FOUND(201, "api.error.room.not.found"),
    PLACE_NOT_FOUND(301, "api.error.place.not.found"),
    BOOKING_NOT_FOUND(401, "api.error.booking.not.found"),
    BOOKING_CREATION_FAILED(501, "api.error.booking.creation.failed");

    private final Integer code;
    private final String message;

    ApiErrors(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
