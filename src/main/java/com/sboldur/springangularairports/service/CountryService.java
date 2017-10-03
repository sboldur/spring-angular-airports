package com.sboldur.springangularairports.service;

import com.sboldur.springangularairports.dto.CountryWithAirportsCount;
import com.sboldur.springangularairports.model.Country;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface CountryService {

    Country getCountryByCodeOrName(String codeName);

    List<CountryWithAirportsCount> getTopNCountriesWithHighestNoOfAirports(int topNumber);
    List<CountryWithAirportsCount> getTopNCountriesWithLowestNoOfAirports(int topNumber);

    Map<Country, List<String>> getRunwaySurfacesPerAllCountries();

}
