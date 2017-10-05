package com.sboldur.springangularairports.service;

import com.sboldur.springangularairports.exceptions.EntityNotFoundException;
import com.sboldur.springangularairports.model.Airport;
import com.sboldur.springangularairports.model.Country;
import com.sboldur.springangularairports.repository.AirportRepository;
import com.sboldur.springangularairports.repository.CountryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AirportServiceTest {

    @Mock
    private AirportRepository airportRepositoryMock;
    @Mock
    private CountryRepository countryRepositoryMock;

    private AirportService airportService;

    @Before
    public void initialize() {
        airportService = new AirportServiceImpl(airportRepositoryMock, countryRepositoryMock);
    }

    @Test
    public void shouldCallFindAirportsByCountryCodeOrNAmeOnce(){
        List<Airport> airports = Arrays.asList(new Airport());
        Mockito.when(countryRepositoryMock.findCountryByCodeIgnoreCaseOrNameIgnoreCase(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(new Country());
        Mockito.when(airportRepositoryMock.findByCountryCodeIgnoreCaseOrCountryNameIgnoreCase(Mockito.anyString(),Mockito.anyString())).thenReturn(airports);

        airportService.getByCountryCodeOrName("AT", null);

        Mockito.verify(airportRepositoryMock, Mockito.times(1)).findByCountryCodeIgnoreCaseOrCountryNameIgnoreCase(Mockito.anyString(),Mockito.anyString());
        Mockito.verify(countryRepositoryMock, Mockito.times(1)).findCountryByCodeIgnoreCaseOrNameIgnoreCase(Mockito.anyString(),Mockito.anyString());
    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowCountryNotFound(){
        Mockito.when(countryRepositoryMock.findCountryByCodeIgnoreCaseOrNameIgnoreCase(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(null);
        airportService.getByCountryCodeOrName("AT", "");

        Mockito.verify(countryRepositoryMock, Mockito.times(1)).findCountryByCodeIgnoreCaseOrNameIgnoreCase(Mockito.any(String.class),Mockito.any(String.class));
        Mockito.verify(airportRepositoryMock, Mockito.never()).findByCountryCodeIgnoreCaseOrCountryNameIgnoreCase(Mockito.any(String.class),Mockito.any(String.class));
    }

}
