package pl.book.it.api.model.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {

    @NotNull
    private LocalDate dateFrom;
    @NotNull
    private LocalDate dateTo;
    @NotNull
    private String userEmail;
    @NotNull
    private Long placeId;
    @NotNull
    private Long roomId;
    @JsonIgnore
    @AssertTrue(message = "Booking start date need to be before ending date")
    public boolean isDateFromBeforeDateTo() {
        return dateFrom.isBefore(dateTo);
    }
}
