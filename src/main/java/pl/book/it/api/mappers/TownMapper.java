package pl.book.it.api.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.book.it.api.domain.Town;
import pl.book.it.api.model.Dto.TownDto;

@Mapper
public interface TownMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "id", target = "id")
    Town toTown(TownDto townDto);

    @InheritInverseConfiguration
    TownDto toTownDto(Town town);

}
