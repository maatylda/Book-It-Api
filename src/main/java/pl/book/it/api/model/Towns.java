package pl.book.it.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.book.it.api.domain.Town;
import pl.book.it.api.model.Dto.TownDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Towns {

    private List<TownDto> towns;
}
