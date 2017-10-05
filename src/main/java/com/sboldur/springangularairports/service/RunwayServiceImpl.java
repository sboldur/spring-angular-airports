package com.sboldur.springangularairports.service;

import com.sboldur.springangularairports.dto.RunwayWithIdentificationsCount;
import com.sboldur.springangularairports.exceptions.EntityNotFoundException;
import com.sboldur.springangularairports.exceptions.InvalidTopException;
import com.sboldur.springangularairports.repository.CountryRepository;
import com.sboldur.springangularairports.repository.RunwayRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RunwayServiceImpl implements RunwayService {

    private static final Logger logger = LoggerFactory.getLogger(RunwayServiceImpl.class);
    private RunwayRepository runwayRepository;
    private CountryRepository countryRepository;

    @Autowired
    public RunwayServiceImpl(RunwayRepository runwayRepository, CountryRepository countryRepository) {
        this.runwayRepository = runwayRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<RunwayWithIdentificationsCount> getTopNMostCommonIdentifications(int topNumber) {
        logger.info("requesting top {} most common identification", topNumber);
        if (topNumber < 1) {
            logger.info("The requested top {} for most common identifications is invalid", topNumber);
            throw new InvalidTopException("The top " + topNumber + " number is invalid, must request at least top 1 most common identifications");
        }
        return this.runwayRepository.findByMostCommonIdentifications(new PageRequest(0, topNumber));
    }

    @Override
    public List<String> getRunwaySurfacesByCountry(String countryCode) {
        logger.info("Fetching types of runway surfaces of country {}", countryCode);
        if (countryRepository.findCountryByCodeIgnoreCaseOrNameIgnoreCase(countryCode, countryCode) == null) {
            logger.info("Unable to fetch runway surfaces. Country {} was not found", countryCode);
            throw new EntityNotFoundException("Country with code or name: " + countryCode + " was not found ");
        }
        return this.runwayRepository.findDistinctRunwaySurfaceByIsoCountry(countryCode);
    }

}
