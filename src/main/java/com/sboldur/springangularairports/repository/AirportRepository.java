package com.sboldur.springangularairports.repository;

import com.sboldur.springangularairports.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {


    List<Airport> findByCountryCodeOrCountryName(String code, String name);


}
