package pl.book.it.api.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.book.it.api.domain.Place;
import pl.book.it.api.model.Dto.PlaceDto;

@Mapper(config = IgnoreUnmappedMapperConfig.class)
public interface PlaceMapStructMapper {

    @Mapping(source = "townName",target = "town.name")
    Place toPlace (PlaceDto placeDto);

    @InheritInverseConfiguration
    PlaceDto toPlaceDto (Place place);
}
