package pl.book.it.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.book.it.api.domain.User;
import pl.book.it.api.model.Dto.UserDto;

@Mapper(config = IgnoreUnmappedMapperConfig.class)
public interface UserMapStructMapper {

    //czy to jest ok?
    @Mapping(target = "password",constant = "***** ***")
    UserDto toUserDto (User user);

    @Mapping(target = "password",ignore = true)
    User toUser (UserDto userDto);


}
