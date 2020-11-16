package pl.book.it.api.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.book.it.api.bootstrap.TestConsts;
import pl.book.it.api.model.Bookings;
import pl.book.it.api.model.Dto.BookingDto;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BookingControllerTest extends AbstractSpringTest {

    @Test
    void shouldGetAllUsersBookings() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(WebConstants.API_BOOKINGS_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .param("email", TestConsts.EMAIL_1))
                .andExpect(status().isOk()).andReturn();
        final String contentAsString = mvcResult.getResponse().getContentAsString();
        final Bookings bookings = objectMapper.readValue(contentAsString, Bookings.class);

        assertThat(bookings.getBookings().size()).isEqualTo(5);
        assertThat(bookings.getBookings().stream()
                .map(BookingDto::getUserEmail)
                .collect(Collectors.toList())).allMatch(TestConsts.EMAIL_1::equals);
    }

}