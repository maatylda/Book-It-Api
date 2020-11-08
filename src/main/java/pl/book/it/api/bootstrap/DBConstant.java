package pl.book.it.api.bootstrap;

import java.time.LocalDate;

public interface DBConstant {
    String PHONE_NUMBER_1 = "123123123";
    String FIRST_NAME_1 = "Anna";
    String LAST_NAME_1 = "Kowalska";
    String EMAIL_1 = "akow@akow.com";
    String PASSWORD_1 = "qwerty";
    String TOWN_NAME_1 = "GDYNIA";
    String TOWN_NAME_2 = "SOPOT";
    String HOTEL_NAME_1 = "Hotel1";
    String HOTEL_NAME_2 = "Hotel2";
    String HOTEL_DESCRIPTION_1 = "testHotel";
    String HOTEL_DESCRIPTION_2 = "testHotel2";
    LocalDate BOOKING_DATE_FROM_1 = LocalDate.of(2020, 11, 1);
    LocalDate BOOKING_DATE_FROM_2 = LocalDate.of(2020, 11, 3);
    LocalDate BOOKING_DATE_FROM_3 = LocalDate.of(2020, 10, 30);
    LocalDate BOOKING_DATE_FROM_4 = LocalDate.of(2020, 11, 5);
    LocalDate BOOKING_DATE_FROM_5 = LocalDate.of(2020, 10, 30);
    LocalDate BOOKING_DATE_TO_1 = LocalDate.of(2020, 11, 5);
    LocalDate BOOKING_DATE_TO_2 = LocalDate.of(2020, 11, 4);
    LocalDate BOOKING_DATE_TO_3 = LocalDate.of(2020, 11, 7);
    LocalDate BOOKING_DATE_TO_4 = LocalDate.of(2020, 11, 6);
    LocalDate BOOKING_DATE_TO_5 = LocalDate.of(2020, 11, 2);

}
