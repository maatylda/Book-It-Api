package pl.book.it.api.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.book.it.api.bootstrap.TestConsts;
import pl.book.it.api.model.Places;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PlaceControllerTest extends AbstractSpringTest {

    @Test
    void shouldGetAllPlaces() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(WebConstants.API_PLACES_PATH)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        final String contentAsString = mvcResult.getResponse().getContentAsString();
        final Places places = objectMapper.readValue(contentAsString, Places.class);
        assertThat(places.getPlaces().stream().map(p -> p.getTown().getName()).collect(Collectors.toList()))
                .contains(TestConsts.TOWN_NAME_1)
                .contains(TestConsts.TOWN_NAME_2);
        assertThat(places.getPlaces().size()).isEqualTo(2);
    }

    @Test
    void shouldGetAllPlacesInTownByName() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(WebConstants.API_PLACES_PATH + "/towns/Gdynia")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        final String contentAsString = mvcResult.getResponse().getContentAsString();
        final Places places = objectMapper.readValue(contentAsString, Places.class);
        assertThat(places.getPlaces().stream().map(p -> p.getTown().getName()).collect(Collectors.toList()))
                .contains(TestConsts.TOWN_NAME_1);
        assertThat(places.getPlaces().size()).isEqualTo(1);
    }

    @Test
    void shouldFindPlacesInTownAvailableInDates() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(WebConstants.API_PLACES_PATH + "/search")
                .accept(MediaType.APPLICATION_JSON)
                .param("from", "2022-10-10")
                .param("to", "2022-10-15")
                .param("town", "Gdynia"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.places").isArray())
                .andExpect(jsonPath("$.places", hasSize(1)));
    }

    @Test
    void shouldNotFindPlaceInTownAvailableInDates() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(WebConstants.API_PLACES_PATH + "/search")
                .accept(MediaType.APPLICATION_JSON)
                .param("from", "2020-11-02")
                .param("to", "2020-11-29")
                .param("town", "Gdynia"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.places").isArray())
                .andExpect(jsonPath("$.places", hasSize(0)))
                .andExpect(jsonPath("$.places").isEmpty());
    }

    @Test
    void shouldNotFindPlaceInTownInDates() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(WebConstants.API_PLACES_PATH + "/search")
                .accept(MediaType.APPLICATION_JSON)
                .param("from", "2020-10-20")
                .param("to", "2020-11-01")
                .param("town", "Gdynia"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.places").isArray())
                .andExpect(jsonPath("$.places", hasSize(0)))
                .andExpect(jsonPath("$.places").isEmpty());
    }

}
