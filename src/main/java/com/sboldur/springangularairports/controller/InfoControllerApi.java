package com.sboldur.springangularairports.controller;

import com.sboldur.springangularairports.dto.ReportResponse;
import com.sboldur.springangularairports.model.Airport;
import com.sboldur.springangularairports.service.AirportService;
import com.sboldur.springangularairports.service.CountryService;
import com.sboldur.springangularairports.service.RunwayService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InfoControllerApi {
    private static final Logger logger = LoggerFactory.getLogger(InfoControllerApi.class);

    @Autowired
    private AirportService airportService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private RunwayService runwayService;

    @ApiOperation(value = "retrieve all airports with runways for the provided country code or name")
    @RequestMapping(value = "/query/{countryCodeOrName}", method = RequestMethod.GET)
    public ResponseEntity<List<Airport>> getAirportsAndRunwaysOfCountry(@PathVariable("countryCodeOrName") String countryCodeOrName) {
        logger.info("fetching all airports by country code or name {}", countryCodeOrName);
        List<Airport> airports = airportService.getByCountryCodeOrName(countryCodeOrName, countryCodeOrName);
        return new ResponseEntity<List<Airport>>(airports, HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve varius reports")
    @RequestMapping(value = "/reports", method = RequestMethod.GET)
    public ResponseEntity<ReportResponse> getReport(@RequestParam(value = "topNo", defaultValue = "10") int topNo) {
        logger.info("Building report response");
        ReportResponse reportResponse = new ReportResponse();
        logger.info("Fetching top {} countries with highest number of airports", topNo);
        reportResponse.setCountriesWithHighestNoOfAirports(countryService.getTopNCountriesWithHighestNoOfAirports(topNo));
        logger.info("Fetching top {} countries with lowest number of airports", topNo);
        reportResponse.setCountriesWithLowestNoOfAirports(countryService.getTopNCountriesWithLowestNoOfAirports(topNo));
        logger.info("Fetching types of runway surfaces per each country");
        reportResponse.setRunwaySurfacesPerCountry(countryService.getRunwaySurfacesPerAllCountries());
        logger.info("Fetching top {} most common runway identifications", topNo);
        reportResponse.setMostCommonRunwayIdentifications(runwayService.getTopNMostCommonIdentifications(topNo));

        return new ResponseEntity<ReportResponse>(reportResponse, HttpStatus.OK);

    }


}
