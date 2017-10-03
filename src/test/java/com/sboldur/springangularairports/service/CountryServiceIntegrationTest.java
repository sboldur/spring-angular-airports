package com.sboldur.springangularairports.service;

import com.sboldur.springangularairports.dto.CountryWithAirportsCount;
import com.sboldur.springangularairports.model.Country;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryServiceIntegrationTest {

    @Autowired
    private CountryService countryService;

    @Test
    public void shouldGetCountryByCode() {
        Country country = countryService.getCountryByCodeOrName("AE");
        Assertions.assertThat(country).isNotNull();
        Assertions.assertThat(country.getCode()).isEqualTo("AE");
        Assertions.assertThat(country.getName()).isEqualTo("United Arab Emirates");
        Assertions.assertThat(country.getContinent()).isEqualTo("AS");
    }

    @Test
    public void shouldGetCountryByName() {
        Country country = countryService.getCountryByCodeOrName("Belgium");
        assertThat(country).isNotNull();
        assertThat(country.getCode()).isEqualTo("BE");
        assertThat(country.getName()).isEqualTo("Belgium");
        assertThat(country.getContinent()).isEqualTo("EU");
    }

    @Test
    public void shouldGetTop2CountriesWithHighestNoOfAirports() {
        List<CountryWithAirportsCount> countryWithAirportsCounts = countryService.getTopNCountriesWithHighestNoOfAirports(2);
        Assertions.assertThat(countryWithAirportsCounts.size()).isEqualTo(2);
        Assertions.assertThat(countryWithAirportsCounts.get(0).getCountry().getName()).isEqualTo("Austria");
        Assertions.assertThat(countryWithAirportsCounts.get(0).getAirportsCount()).isEqualTo(8);

        Assertions.assertThat(countryWithAirportsCounts.get(1).getCountry().getName()).isEqualTo("United Arab Emirates");
        Assertions.assertThat(countryWithAirportsCounts.get(1).getAirportsCount()).isEqualTo(5);
    }

    @Test
    public void shouldGetTop2CountriesWithLowestNoOfAirports() {
        List<CountryWithAirportsCount> countryWithAirportsCounts = countryService.getTopNCountriesWithLowestNoOfAirports(2);
        Assertions.assertThat(countryWithAirportsCounts.size()).isEqualTo(2);
        Assertions.assertThat(countryWithAirportsCounts.get(0).getCountry().getName()).isEqualTo("Belgium");
        Assertions.assertThat(countryWithAirportsCounts.get(0).getAirportsCount()).isEqualTo(1);

        Assertions.assertThat(countryWithAirportsCounts.get(1).getCountry().getName()).isEqualTo("Australia");
        Assertions.assertThat(countryWithAirportsCounts.get(1).getAirportsCount()).isEqualTo(2);
    }


    @Test
    public void shouldGetRunwaySurfacesForAllCountries() {
        Map<Country, List<String>> countryWithSurfaces = countryService.getRunwaySurfacesPerAllCountries();

        assertThat(countryWithSurfaces.size()).isEqualTo(5);

        Country andorraCountry = countryService.getCountryByCodeOrName("AD");
        Assertions.assertThat(countryWithSurfaces.get(andorraCountry)).containsExactlyInAnyOrder("GRVL", "ASPH-G", "TURF", "GRASS");

        Country arabEmiratesCountry = countryService.getCountryByCodeOrName("AE");
        Assertions.assertThat(countryWithSurfaces.get(arabEmiratesCountry)).containsExactlyInAnyOrder("GRAVEL","ASPH", "TURF-G", "TURF");

        Country austriaCountry = countryService.getCountryByCodeOrName("AT");
        Assertions.assertThat(countryWithSurfaces.get(austriaCountry)).containsExactlyInAnyOrder("ASPH", "ASPH-G", "TURF");

        Country australiaCountry = countryService.getCountryByCodeOrName("AU");
        Assertions.assertThat(countryWithSurfaces.get(australiaCountry)).isEmpty();

        Country belgiumCountry = countryService.getCountryByCodeOrName("BE");
        Assertions.assertThat(countryWithSurfaces.get(belgiumCountry)).isEmpty();
    }
}
