package pl.book.it.api.model.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String street;

    @NotNull
    private String streetNumber;

    @NotNull
    private String zipCode;

    @NotNull
    private String townName;
}
