package pl.book.it.api.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import pl.book.it.api.bootstrap.TestConsts;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TownControllerTest extends AbstractSpringTest {

    @Test
    void shouldGetAllTowns() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get(WebConstants.API_TOWNS_PATH)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.towns").isArray())
                .andExpect(jsonPath("$.towns", hasSize(2)))
                .andExpect(jsonPath("$.towns[0].name", equalTo(TestConsts.TOWN_NAME_1)))
                .andExpect(jsonPath("$.towns[1].name", equalTo(TestConsts.TOWN_NAME_2)))
                .andReturn();
    }
}/*
           final String townsAsString = mvcResult.getResponse().getContentAsString();
        final Towns actualTowns = objectMapper.readValue(townsAsString, Towns.class);

        assertThat(actualTowns.getTowns()).hasSize(2);
        assertThat(actualTowns.getTowns()
                .stream().map(Town::getName)
                .collect(Collectors.toList()))
                .contains(TestConsts.TOWN_NAME_1)
                .contains(TestConsts.TOWN_NAME_2);*/


