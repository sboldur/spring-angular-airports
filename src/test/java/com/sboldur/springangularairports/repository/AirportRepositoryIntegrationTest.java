package com.sboldur.springangularairports.repository;

import com.sboldur.springangularairports.model.Airport;
import com.sboldur.springangularairports.model.Runway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AirportRepositoryIntegrationTest {

    @Autowired
    private AirportRepository airportRepository;

    @Test
    public void shouldHaveData() {
        assertThat(airportRepository.findAll().size()).isEqualTo(20);
    }

    @Test
    public void shouldFindAirportsByCountryName() {
        List<Airport> airports = airportRepository.findByCountryCodeIgnoreCaseOrCountryNameIgnoreCase("", "austria");
        assertThat(airports.size()).isEqualTo(8);

        Set<Runway> runways = new HashSet<>();
        for (Airport airport : airports){
            runways.addAll(airport.getRunways());
        }
        assertThat(runways.size()).isEqualTo(4);
    }

    @Test
    public void shouldFindAirportsByCountryCode() {
        List<Airport> airports = airportRepository.findByCountryCodeIgnoreCaseOrCountryNameIgnoreCase("at", "");
        assertThat(airports.size()).isEqualTo(8);

        Set<Runway> runways = new HashSet<>();
        for (Airport airport : airports){
            runways.addAll(airport.getRunways());
        }
        assertThat(runways.size()).isEqualTo(4);
    }

}
