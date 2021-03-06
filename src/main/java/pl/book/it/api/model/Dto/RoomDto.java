package pl.book.it.api.model.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.book.it.api.model.room.specifications.RoomStandard;
import pl.book.it.api.model.room.specifications.RoomType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {

    private Long id;

    private RoomStandard standard;

    private RoomType roomType;

    @NotNull
    private Double price;

    @NotNull
    private Long placeId;
}
