package pl.book.it.api.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.book.it.api.domain.Booking;
import pl.book.it.api.model.Dto.BookingDto;


@Mapper(config = IgnoreUnmappedMapperConfig.class)
public interface BookingMapper {

    @Mapping(target = "userEmail", source = "user.email")
    @Mapping(target = "placeId", source = "place.id")
    @Mapping(target = "roomId", source = "room.id")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "paid", source = "paid")
    BookingDto toBookingDto(Booking booking);

    @InheritInverseConfiguration
    Booking toBooking(BookingDto bookingDto);
}
