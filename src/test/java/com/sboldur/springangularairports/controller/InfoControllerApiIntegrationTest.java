package com.sboldur.springangularairports.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sboldur.springangularairports.dto.ReportResponse;
import com.sboldur.springangularairports.exceptions.ExceptionResponse;
import com.sboldur.springangularairports.model.Airport;
import com.sboldur.springangularairports.model.Runway;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class InfoControllerApiIntegrationTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setupTest() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void shouldRetrieveAirportsAndRunwaysByCountryCodeOrName() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletResponse queryResponse = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/query/AT")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        AssertionsForClassTypes.assertThat(queryResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
        List<Airport> retrievedAirports = objectMapper.readValue(queryResponse.getContentAsString(), new TypeReference<List<Airport>>(){});
        Set<Runway> runways = new HashSet<>();
        for (Airport airport : retrievedAirports){
            runways.addAll(airport.getRunways());
        }

        Assertions.assertThat(retrievedAirports.size()).isEqualTo(8);
        Assertions.assertThat(runways.size()).isEqualTo(4);
    }

    @Test
    public void shouldRetrieveReports() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletResponse reportHttpResponse = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/reports?topNo=3")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        AssertionsForClassTypes.assertThat(reportHttpResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
        ReportResponse reportResponse = objectMapper.readValue(reportHttpResponse.getContentAsString(), ReportResponse.class);

        Assertions.assertThat(reportResponse.getCountriesWithHighestNoOfAirports().size()).isEqualTo(3);
        Assertions.assertThat(reportResponse.getCountriesWithLowestNoOfAirports().size()).isEqualTo(3);
        Assertions.assertThat(reportResponse.getMostCommonRunwayIdentifications().size()).isEqualTo(3);
        Assertions.assertThat(reportResponse.getRunwaySurfacesPerCountry().size()).isEqualTo(5);
    }

    @Test
    public void shouldHandleCountryEntityNotFound() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletResponse queryResponse = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/query/Zb")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        ExceptionResponse exceptionResponse = objectMapper.readValue(queryResponse.getContentAsString(), ExceptionResponse.class);

        AssertionsForClassTypes.assertThat(queryResponse.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        Assertions.assertThat(exceptionResponse.getErrorCode()).isEqualTo("Not Found" );
        Assertions.assertThat(exceptionResponse.getErrorMessage()).isEqualTo("No country with code Zb or name Zb was found" );

    }

    @Test
    public void shouldHandleInvalidTopNumber() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletResponse reportHttpResponse = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/reports?topNo=0")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        ExceptionResponse exceptionResponse = objectMapper.readValue(reportHttpResponse.getContentAsString(), ExceptionResponse.class);

        AssertionsForClassTypes.assertThat(reportHttpResponse.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        Assertions.assertThat(exceptionResponse.getErrorCode()).isEqualTo("Validation error:" );
        Assertions.assertThat(exceptionResponse.getErrorMessage()).isEqualTo("The top 0 number is invalid, must request at least top 1 countries with highest no. of airports" );

    }

}
