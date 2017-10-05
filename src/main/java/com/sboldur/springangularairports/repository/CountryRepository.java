package com.sboldur.springangularairports.repository;

import com.sboldur.springangularairports.dto.CountryWithAirportsCount;
import com.sboldur.springangularairports.model.Country;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Country findCountryByCodeIgnoreCaseOrNameIgnoreCase(String code, String name);

    @Query("SELECT new com.sboldur.springangularairports.dto.CountryWithAirportsCount(country, count(airports)) FROM Country country LEFT join country.airports airports group by country order by count(airports) DESC, country.code ASC")
    List<CountryWithAirportsCount> findTopCountriesWithHighestNoOfAirports(Pageable pageable);

    @Query("SELECT new com.sboldur.springangularairports.dto.CountryWithAirportsCount(country, count(airports)) FROM Country country  LEFT join country.airports airports group by country order by count(airports) ASC, country.code ASC")
    List<CountryWithAirportsCount> findTopCountriesWithLowestNoOfAirports(Pageable pageable);

}
