package com.sboldur.springangularairports.service;

import com.sboldur.springangularairports.exceptions.EntityNotFoundException;
import com.sboldur.springangularairports.model.Airport;
import com.sboldur.springangularairports.repository.AirportRepository;
import com.sboldur.springangularairports.repository.CountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AirportServiceImpl implements AirportService {

    private static final Logger logger = LoggerFactory.getLogger(AirportServiceImpl.class);
    private AirportRepository airportRepository;
    private CountryRepository countryRepository;

    @Autowired
    public AirportServiceImpl(AirportRepository airportRepository, CountryRepository countryRepository) {
        this.airportRepository = airportRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Airport> getByCountryCodeOrName(String code, String name) {
        logger.info("fetching airports by country code {} or name {}", code, name);
        if (countryRepository.findCountryByCodeOrName(code, name) == null) {
            logger.info("No country with code {} or name {} was found", code, name);
            throw new EntityNotFoundException("No country with code " + code + " or name " + name + " was found");
        }
        return airportRepository.findByCountryCodeIgnoreCaseOrCountryNameIgnoreCase(code, name);
    }
}
