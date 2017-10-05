package com.sboldur.springangularairports.service;

import com.sboldur.springangularairports.dto.CountryWithAirportsCount;
import com.sboldur.springangularairports.exceptions.EntityNotFoundException;
import com.sboldur.springangularairports.exceptions.InvalidTopException;
import com.sboldur.springangularairports.model.Country;
import com.sboldur.springangularairports.repository.CountryRepository;
import com.sboldur.springangularairports.repository.RunwayRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CountryServiceImpl implements CountryService {

    private static final Logger logger = LoggerFactory.getLogger(CountryServiceImpl.class);
    private RunwayRepository runwayRepository;
    private CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(RunwayRepository runwayRepository, CountryRepository countryRepository) {
        this.runwayRepository = runwayRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public Country getCountryByCodeOrName(String codeName) {
        logger.info("finding country {}", codeName);
        Country foundCountry = countryRepository.findCountryByCodeIgnoreCaseOrNameIgnoreCase(codeName, codeName);
        if (foundCountry == null) {
            logger.info("Country {} was not found", codeName);
            throw new EntityNotFoundException("country " + codeName + " was not found");
        }
        logger.debug("found country {}, returning", foundCountry);
        return foundCountry;
    }

    @Override
    public List<CountryWithAirportsCount> getTopNCountriesWithHighestNoOfAirports(int topNumber) {
        logger.info("requesting top {} countries with highest no. of airports", topNumber);
        if (topNumber < 1) {
            logger.info("The requested top {} for countries with highest no. of airports is invalid", topNumber);
            throw new InvalidTopException("The top " + topNumber + " number is invalid, must request at least top 1 countries with highest no. of airports");
        }
        return countryRepository.findTopCountriesWithHighestNoOfAirports(new PageRequest(0, topNumber));
    }

    @Override
    public List<CountryWithAirportsCount> getTopNCountriesWithLowestNoOfAirports(int topNumber) {
        logger.info("requesting top {} countries with lowest no. of airports", topNumber);
        if (topNumber < 1) {
            logger.info("The requested top {} for countries with lowest no. of airports is invalid", topNumber);
            throw new InvalidTopException("The top " + topNumber + " number is invalid, must request at least top 1 countries with lowest no. of airports");
        }
        return countryRepository.findTopCountriesWithLowestNoOfAirports(new PageRequest(0, topNumber));
    }

    @Override
    public Map<Country, List<String>> getRunwaySurfacesPerAllCountries() {
        logger.info("Fetching types of runway surfaces per country");
        Map<Country, List<String>> surfaceTypesPerCountry = new HashMap<>();
        List<Country> countries = countryRepository.findAll();
        for (Country country : countries) {
            logger.debug("Fetching types of runway surfaces of country {}", country.getCode());
            List<String> surfaceTypes = runwayRepository.findDistinctRunwaySurfaceByIsoCountry(country.getCode());
            logger.debug("Found {} types of runway surfaces of country {}", surfaceTypes.size(), country.getCode());
            surfaceTypesPerCountry.put(country, surfaceTypes);
        }
        return surfaceTypesPerCountry;
    }
}
