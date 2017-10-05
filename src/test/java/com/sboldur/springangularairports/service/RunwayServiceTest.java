package com.sboldur.springangularairports.service;

import com.sboldur.springangularairports.dto.RunwayWithIdentificationsCount;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class RunwayServiceTest {

    @Mock
    private RunwayRepository  runwayRepositoryMock;
    @Mock
    private CountryRepository countryRepositoryMock;

    private RunwayService runwayService;

    @Before
    public void initialize() {
        runwayService = new RunwayServiceImpl(runwayRepositoryMock, countryRepositoryMock);
    }


    @Test
    public void shouldCallMostCommonIdentificationsOnce() {
        List<RunwayWithIdentificationsCount> runwayWithIdentificationsCounts = new ArrayList<RunwayWithIdentificationsCount>();
        runwayWithIdentificationsCounts.add(new RunwayWithIdentificationsCount("identification1", 3L));
        Mockito.when(runwayRepositoryMock.findByMostCommonIdentifications(Mockito.any(PageRequest.class))).thenReturn(runwayWithIdentificationsCounts);
        runwayService.getTopNMostCommonIdentifications(2);

        Mockito.verify(runwayRepositoryMock, Mockito.times(1)).findByMostCommonIdentifications(Mockito.any(PageRequest.class));
    }

    @Test
    public void shouldCallGetRunwaySurfacesByCountryOnce() {
        List<String> surfaceTypes =  Arrays.asList("surface1", "surface2" );
        Mockito.when(runwayRepositoryMock.findDistinctRunwaySurfaceByIsoCountry(Mockito.any(String.class))).thenReturn(surfaceTypes);
        Mockito.when(countryRepositoryMock.findCountryByCodeIgnoreCaseOrNameIgnoreCase(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(new Country());
        runwayService.getRunwaySurfacesByCountry("AT");

        Mockito.verify(runwayRepositoryMock, Mockito.times(1)).findDistinctRunwaySurfaceByIsoCountry(Mockito.any(String.class));
    }

    @Test(expected = InvalidTopException.class)
    public void shouldThrowInvalidTopNumber(){
        runwayService.getTopNMostCommonIdentifications(0);
        Mockito.verify(runwayRepositoryMock, Mockito.never()).findByMostCommonIdentifications(Mockito.any(PageRequest.class));
    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowCountryNotFound(){
        Mockito.when(countryRepositoryMock.findCountryByCodeIgnoreCaseOrNameIgnoreCase(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(null);
        runwayService.getRunwaySurfacesByCountry("Austria");

        Mockito.verify(countryRepositoryMock, Mockito.times(1)).findCountryByCodeIgnoreCaseOrNameIgnoreCase(Mockito.any(String.class),Mockito.any(String.class));
        Mockito.verify(runwayRepositoryMock, Mockito.never()).findByMostCommonIdentifications(Mockito.any(PageRequest.class));

    }
}
