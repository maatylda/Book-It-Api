package pl.book.it.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.book.it.api.domain.Place;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Places {
    private List<Place> places;


}
