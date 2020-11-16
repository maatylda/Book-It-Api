package pl.book.it.api.mappers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.book.it.api.bootstrap.TestConsts;
import pl.book.it.api.domain.User;
import pl.book.it.api.model.Dto.UserDto;
import pl.book.it.api.web.AbstractSpringTest;

import static org.assertj.core.api.Assertions.assertThat;


class UserMapStructMapperTest extends AbstractSpringTest {

    @Autowired
    private UserMapStructMapper userMapStructMapper;

    @Test
    void shouldMapUserDtoToUser() {
        User user = User.builder().firstName(TestConsts.FIRST_NAME_1)
                .lastName(TestConsts.LAST_NAME_1)
                .phoneNumber(TestConsts.PHONE_NUMBER_1)
                .email(TestConsts.EMAIL_1)
                .password(TestConsts.PASSWORD_1)
                .build();
        UserDto userDto = UserDto.builder().firstName(TestConsts.FIRST_NAME_1)
                .lastName(TestConsts.LAST_NAME_1)
                .phoneNumber(TestConsts.PHONE_NUMBER_1)
                .email(TestConsts.EMAIL_1)
                .password(TestConsts.PASSWORD_1)
                .build();


        User actualUser = userMapStructMapper.toUser(userDto);
        assertThat(actualUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    void shouldMapUserToUserDtoWithoutRealPassword() {
        User user = User.builder().firstName(TestConsts.FIRST_NAME_1)
                .lastName(TestConsts.LAST_NAME_1)
                .phoneNumber(TestConsts.PHONE_NUMBER_1)
                .email(TestConsts.EMAIL_1)
                .password(TestConsts.PASSWORD_1)
                .build();
        UserDto userDto = UserDto.builder().firstName(TestConsts.FIRST_NAME_1)
                .lastName(TestConsts.LAST_NAME_1)
                .phoneNumber(TestConsts.PHONE_NUMBER_1)
                .email(TestConsts.EMAIL_1)
                .password(TestConsts.PASSWORD_1)
                .build();

        UserDto actualUserDto = userMapStructMapper.toUserDto(user);

        assertThat(actualUserDto.getEmail()).isEqualTo(userDto.getEmail());
        assertThat(actualUserDto.getPassword()).isEqualTo("***** ***");
    }

}