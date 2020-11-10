package pl.book.it.api.model.forms;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.book.it.api.domain.Picture;
import pl.book.it.api.domain.Room;

import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceForm {

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
