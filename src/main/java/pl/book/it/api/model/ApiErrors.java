package pl.book.it.api.model;

import lombok.Getter;

@Getter
public enum ApiErrors {
    USER_WITH_GIVEN_EMAIL_ALREADY_EXIST("101", "api.error.user.email.taken"),
    USER_NOT_FOUND("102", "api.error.user.not.found"),
    ROOM_NOT_FOUND("201", "api.error.room.not.found"),
    PLACE_NOT_FOUND("301", "api.error.place.not.found"),
    PLACE_CREATION_FAILED("302", "api.error.place.creation.faiel"),
    BOOKING_NOT_FOUND("401", "api.error.booking.not.found"),
    BOOKING_CREATION_FAILED("501", "api.error.booking.creation.failed"),
    TOWN_NOT_FOUND("601", "api.error.town.not.found");

    private final String code;
    private final String message;

    ApiErrors(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
