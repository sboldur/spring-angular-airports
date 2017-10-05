package com.sboldur.springangularairports.repository;

import com.sboldur.springangularairports.dto.CountryWithAirportsCount;
import com.sboldur.springangularairports.model.Airport;
import com.sboldur.springangularairports.model.Country;
import com.sboldur.springangularairports.model.Runway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CountryRepositoryIntegrationTest {

    @Autowired
    private CountryRepository countryRepository;

    @Test
    public void shouldHaveData(){
        assertThat(countryRepository.findAll().size()).isEqualTo(5);
    }

    @Test
    public void shouldReturnTop2CountriesByHighestNoOfAirports(){
        List<CountryWithAirportsCount> topCountries = countryRepository.findTopCountriesWithHighestNoOfAirports(new PageRequest(0,2));

        assertThat(topCountries.size()).isEqualTo(2);
        assertThat(topCountries.get(0).getCountry().getName()).isEqualTo("Austria");
        assertThat(topCountries.get(0).getAirportsCount()).isEqualTo(8);

        assertThat(topCountries.get(1).getCountry().getName()).isEqualTo("United Arab Emirates");
        assertThat(topCountries.get(1).getAirportsCount()).isEqualTo(5);

    }

    @Test
    public void shouldReturnTop2CountriesByLowestNoOfAirports(){
        List<CountryWithAirportsCount> topCountries = countryRepository.findTopCountriesWithLowestNoOfAirports(new PageRequest(0,2));

        assertThat(topCountries.size()).isEqualTo(2);
        assertThat(topCountries.get(0).getCountry().getName()).isEqualTo("Belgium");
        assertThat(topCountries.get(0).getAirportsCount()).isEqualTo(1);

        assertThat(topCountries.get(1).getCountry().getName()).isEqualTo("Australia");
        assertThat(topCountries.get(1).getAirportsCount()).isEqualTo(2);
    }
}
