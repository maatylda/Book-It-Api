package pl.book.it.api.model;

public enum ApiErrors {
    USER_WITH_GIVEN_EMAIL_ALREADY_EXIST(101,"api.error.user.email.taken");


    ApiErrors(Integer code, String message) {
    }
}
