package com.sboldur.springangularairports.dto;

import com.sboldur.springangularairports.model.Country;

import java.io.Serializable;


public class CountryWithAirportsCount implements Serializable{
    private Country country;
    private long airportsCount;

    public CountryWithAirportsCount() {
    }

    public CountryWithAirportsCount(Country country, long airportsCount) {
        this.country = country;
        this.airportsCount = airportsCount;
    }

    public Country getCountry() {
        return country;
    }

    public long getAirportsCount() {
        return airportsCount;
    }
}
