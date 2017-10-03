package com.sboldur.springangularairports.service;

import com.sboldur.springangularairports.model.Airport;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirportServiceIntegrationTest {

    @Autowired
    private AirportService airportService;

    @Test
    public void shouldGetAirportsOfAustralia(){
        List<Airport> airports = airportService.getByCountryCodeOrName("AU",null);
        Assertions.assertThat(airports.size()).isEqualTo(2);
        Assertions.assertThat(airports.get(0).getId()).isEqualTo(6541L);
        Assertions.assertThat(airports.get(1).getId()).isEqualTo(6542L);
    }
}
