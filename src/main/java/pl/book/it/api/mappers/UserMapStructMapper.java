package pl.book.it.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.book.it.api.domain.User;
import pl.book.it.api.model.Dto.UserDto;

@Mapper(config = IgnoreUnmappedMapperConfig.class)
public interface UserMapStructMapper {

    //password should not be mapped
    UserDto toUserDto(User user);

    User toUser(UserDto userDto);
}
