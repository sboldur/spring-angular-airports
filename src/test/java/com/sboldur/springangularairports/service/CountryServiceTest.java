package com.sboldur.springangularairports.service;

import com.sboldur.springangularairports.dto.CountryWithAirportsCount;
import com.sboldur.springangularairports.exceptions.EntityNotFoundException;
import com.sboldur.springangularairports.exceptions.InvalidTopException;
import com.sboldur.springangularairports.model.Country;
import com.sboldur.springangularairports.repository.CountryRepository;
import com.sboldur.springangularairports.repository.RunwayRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CountryServiceTest {

    @Mock
    private RunwayRepository runwayRepositoryMock;
    @Mock
    private CountryRepository countryRepositoryMock;

    private CountryService countryService;

    @Before
    public void initialize() {
        countryService = new CountryServiceImpl(runwayRepositoryMock, countryRepositoryMock);
    }

    @Test
    public void shouldCallGetCountryByCodeOrNameOnce() {
        Mockito.when(countryRepositoryMock.findCountryByCodeIgnoreCaseOrNameIgnoreCase(Mockito.anyString(), Mockito.anyString())).thenReturn(new Country());

        countryService.getCountryByCodeOrName("Austria");

        Mockito.verify(countryRepositoryMock, Mockito.times(1)).findCountryByCodeIgnoreCaseOrNameIgnoreCase(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    public void shouldCallTopCountriesWithHighestNoOfAirportsOnce() {
        List<CountryWithAirportsCount> countriesWithAirportsCount = Arrays.asList(new CountryWithAirportsCount(new Country(), 5));
        Mockito.when(countryRepositoryMock.findTopCountriesWithHighestNoOfAirports(Mockito.any(PageRequest.class))).thenReturn(countriesWithAirportsCount);

        countryService.getTopNCountriesWithHighestNoOfAirports(3);

        Mockito.verify(countryRepositoryMock, Mockito.times(1)).findTopCountriesWithHighestNoOfAirports(Mockito.any(PageRequest.class));

    }

    @Test
    public void shouldCallTopCountriesWithLowestNoOfAirportsOnce() {
        List<CountryWithAirportsCount> countriesWithAirportsCount = Arrays.asList(new CountryWithAirportsCount(new Country(), 2));
        Mockito.when(countryRepositoryMock.findTopCountriesWithLowestNoOfAirports(Mockito.any(PageRequest.class))).thenReturn(countriesWithAirportsCount);

        countryService.getTopNCountriesWithLowestNoOfAirports(3);

        Mockito.verify(countryRepositoryMock, Mockito.times(1)).findTopCountriesWithLowestNoOfAirports(Mockito.any(PageRequest.class));

    }

    @Test
    public void shouldCallRunwaySurfacesPerAllCountriesOnce() {
        List<Country> countries = Arrays.asList(new Country("code", "name", "continent"));
        Mockito.when(countryRepositoryMock.findAll()).thenReturn(countries);
        Mockito.when(runwayRepositoryMock.findDistinctRunwaySurfaceByIsoCountry(Mockito.any(String.class))).thenReturn(Arrays.asList("surface1","surface2"));

        countryService.getRunwaySurfacesPerAllCountries();

        Mockito.verify(countryRepositoryMock, Mockito.times(1)).findAll();
        Mockito.verify(runwayRepositoryMock, Mockito.times(1)).findDistinctRunwaySurfaceByIsoCountry(Mockito.any(String.class));

    }

    @Test(expected = InvalidTopException.class)
    public void shouldThrowInvalidTopNumberForHighestNoOfAirports(){
        countryService.getTopNCountriesWithHighestNoOfAirports(0);
        Mockito.verify(countryRepositoryMock, Mockito.never()).findTopCountriesWithHighestNoOfAirports(Mockito.any(PageRequest.class));
    }

    @Test(expected = InvalidTopException.class)
    public void shouldThrowInvalidTopNumberForLowestNoOfAirports(){
        countryService.getTopNCountriesWithLowestNoOfAirports(0);
        Mockito.verify(countryRepositoryMock, Mockito.never()).findTopCountriesWithLowestNoOfAirports(Mockito.any(PageRequest.class));
    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowCountryNotFound(){
        Mockito.when(countryRepositoryMock.findCountryByCodeIgnoreCaseOrNameIgnoreCase(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(null);
        countryService.getCountryByCodeOrName("Austria");

        Mockito.verify(countryRepositoryMock, Mockito.never()).findCountryByCodeIgnoreCaseOrNameIgnoreCase(Mockito.any(String.class),Mockito.any(String.class));
    }
}
