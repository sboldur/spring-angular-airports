package com.sboldur.springangularairports.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sboldur.springangularairports.dto.CountryWithAirportsCount;
import com.sboldur.springangularairports.dto.RunwayWithIdentificationsCount;
import com.sboldur.springangularairports.exceptions.EntityNotFoundException;
import com.sboldur.springangularairports.exceptions.InvalidTopException;
import com.sboldur.springangularairports.model.Airport;
import com.sboldur.springangularairports.model.Country;
import com.sboldur.springangularairports.service.AirportService;
import com.sboldur.springangularairports.service.CountryService;
import com.sboldur.springangularairports.service.RunwayService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@WebAppConfiguration
public class InfoControllerApiTest {
    private MockMvc mockMvc;

    @Mock
    private CountryService countryServiceMock;
    @Mock
    private AirportService airportServiceMock;
    @Mock
    private RunwayService runwayServiceMock;

    @InjectMocks
    private InfoControllerApi infoControllerApi;

    @Before
    public void initialize(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(infoControllerApi)
                .setControllerAdvice(new InfoControllerAdvice())
                .build();
    }

    @Test
    public void shouldCallOnlyAirportServiceOnceWhenQuering() throws Exception {
        List<Airport> airports = Arrays.asList(new Airport());
        Mockito.when(airportServiceMock.getByCountryCodeOrName(Mockito.anyString(),Mockito.anyString())).thenReturn(airports);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/query/AT")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));


        Mockito.verify(airportServiceMock, Mockito.times(1)).getByCountryCodeOrName(Mockito.anyString(),Mockito.anyString());
        Mockito.verifyNoMoreInteractions(airportServiceMock);

    }

    @Test
    public void shouldCallCountryService3TimesAndRunwayServiceOnce() throws Exception {
        Map<Country, List<String>> runwaySurfacesPerCountry = new HashMap<>();
        runwaySurfacesPerCountry.put(new Country(), Arrays.asList("surface1", "surface2"));

        Mockito.when(countryServiceMock.getTopNCountriesWithHighestNoOfAirports(Mockito.anyInt())).thenReturn(Arrays.asList(new CountryWithAirportsCount(new Country(),7)));
        Mockito.when(countryServiceMock.getTopNCountriesWithLowestNoOfAirports(Mockito.anyInt())).thenReturn(Arrays.asList(new CountryWithAirportsCount(new Country(), 1)));
        Mockito.when(countryServiceMock.getRunwaySurfacesPerAllCountries()).thenReturn(runwaySurfacesPerCountry);
        Mockito.when(runwayServiceMock.getTopNMostCommonIdentifications(Mockito.anyInt())).thenReturn(Arrays.asList(new RunwayWithIdentificationsCount("ident1", 3L)));
        mockMvc.perform(MockMvcRequestBuilders
                .get("/reports")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        Mockito.verify(countryServiceMock, Mockito.times(1)).getTopNCountriesWithHighestNoOfAirports(Mockito.anyInt());
        Mockito.verify(countryServiceMock, Mockito.times(1)).getTopNCountriesWithLowestNoOfAirports(Mockito.anyInt());
        Mockito.verify(countryServiceMock, Mockito.times(1)).getRunwaySurfacesPerAllCountries();
        Mockito.verifyNoMoreInteractions(countryServiceMock);
        Mockito.verify(runwayServiceMock, Mockito.times(1)).getTopNMostCommonIdentifications(Mockito.anyInt());
        Mockito.verifyNoMoreInteractions(runwayServiceMock);

    }


    @Test
    public void shouldCatchInvalidTop() throws Exception {
        Mockito.when(countryServiceMock.getTopNCountriesWithHighestNoOfAirports(Mockito.anyInt())).thenThrow(new InvalidTopException("invalid top number"));
        mockMvc.perform(MockMvcRequestBuilders
                .get("/reports")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));
        Mockito.verify(countryServiceMock, Mockito.times(1)).getTopNCountriesWithHighestNoOfAirports(Mockito.anyInt());
        Mockito.verifyNoMoreInteractions(countryServiceMock);
        Mockito.verifyZeroInteractions(runwayServiceMock);

    }

    @Test
    public void shouldCatchEntityNotFoundWhenMakingQuery() throws Exception {
        Mockito.when(airportServiceMock.getByCountryCodeOrName(Mockito.anyString(),Mockito.anyString())).thenThrow(new EntityNotFoundException("No country with this code was found"));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/query/AT")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        Mockito.verify(airportServiceMock, Mockito.times(1)).getByCountryCodeOrName(Mockito.anyString(),Mockito.anyString());
        Mockito.verifyNoMoreInteractions(airportServiceMock);
    }
}
