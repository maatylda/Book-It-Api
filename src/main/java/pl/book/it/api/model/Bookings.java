package pl.book.it.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.book.it.api.model.Dto.BookingDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bookings {

    private List<BookingDto> bookings;
}
