package com.sboldur.springangularairports.dto;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sboldur.springangularairports.model.Country;
import com.sboldur.springangularairports.util.CountryKeyMapDeserializer;
import com.sboldur.springangularairports.util.CountryKeyMapSerializer;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ReportResponse implements Serializable {
    List<CountryWithAirportsCount> countriesWithHighestNoOfAirports;
    List<CountryWithAirportsCount> countriesWithLowestNoOfAirports;

    @JsonDeserialize(keyUsing = CountryKeyMapDeserializer.class)
    @JsonSerialize(keyUsing = CountryKeyMapSerializer.class)
    Map<Country, List<String>> runwaySurfacesPerCountry;
    List<RunwayWithIdentificationsCount> mostCommonRunwayIdentifications;

    public List<CountryWithAirportsCount> getCountriesWithHighestNoOfAirports() {
        return countriesWithHighestNoOfAirports;
    }

    public List<CountryWithAirportsCount> getCountriesWithLowestNoOfAirports() {
        return countriesWithLowestNoOfAirports;
    }

    public Map<Country, List<String>> getRunwaySurfacesPerCountry() {
        return runwaySurfacesPerCountry;
    }

    public List<RunwayWithIdentificationsCount> getMostCommonRunwayIdentifications() {
        return mostCommonRunwayIdentifications;
    }

    public void setCountriesWithHighestNoOfAirports(List<CountryWithAirportsCount> countriesWithHighestNoOfAirports) {
        this.countriesWithHighestNoOfAirports = countriesWithHighestNoOfAirports;
    }

    public void setCountriesWithLowestNoOfAirports(List<CountryWithAirportsCount> countriesWithLowestNoOfAirports) {
        this.countriesWithLowestNoOfAirports = countriesWithLowestNoOfAirports;
    }

    public void setRunwaySurfacesPerCountry(Map<Country, List<String>> runwaySurfacesPerCountry) {
        this.runwaySurfacesPerCountry = runwaySurfacesPerCountry;
    }

    public void setMostCommonRunwayIdentifications(List<RunwayWithIdentificationsCount> mostCommonRunwayIdentifications) {
        this.mostCommonRunwayIdentifications = mostCommonRunwayIdentifications;
    }
}
