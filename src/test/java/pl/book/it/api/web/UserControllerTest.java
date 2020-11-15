package pl.book.it.api.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import pl.book.it.api.model.Dto.UserDto;
import pl.book.it.api.model.ErrorMessage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends AbstractSpringTest {

    @Test
    void shouldCreateUser() throws Exception {
        //given
        final UserDto user = UserDto.builder()
                .email("a@gmail.com")
                .firstName("Anna")
                .lastName("Nowak")
                .password("12345678")
                .phoneNumber("1234")
                .build();
        final String userAsString = objectMapper.writeValueAsString(user);

        //when and then
        final MvcResult mvcResult = mockMvc.perform(post(WebConstants.API_USERS_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(userAsString))
                .andDo(log())
                .andExpect(status().isCreated())
                .andReturn();
        final String createdUserAsString = mvcResult.getResponse().getContentAsString();
        final UserDto userDto = objectMapper.readValue(createdUserAsString, UserDto.class);
        assertThat(userDto.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(userDto.getLastName()).isEqualTo(user.getLastName());
        assertThat(userDto.getEmail()).isEqualTo(user.getEmail());
        assertThat(userDto.getPhoneNumber()).isEqualTo(user.getPhoneNumber());
        assertThat(userDto.getPassword()).isNotNull();
    }

    @Test
    void shouldReturnBadRequestWhenCreatingInvalidUser() throws Exception {
        final UserDto invalidUser = UserDto.builder().build();
        final String invalidUserAsString = objectMapper.writeValueAsString(invalidUser);
        final MvcResult mvcResult = mockMvc.perform(post(WebConstants.API_USERS_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(invalidUserAsString))
                .andExpect(status().isBadRequest())
                .andReturn();
        final String contentAsString = mvcResult.getResponse().getContentAsString();
        final ErrorMessage errorMessage = objectMapper.readValue(contentAsString, ErrorMessage.class);
        assertThat(errorMessage.getErrors()).hasSize(3);
    }
}