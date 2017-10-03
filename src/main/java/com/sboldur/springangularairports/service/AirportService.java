package com.sboldur.springangularairports.service;

import com.sboldur.springangularairports.model.Airport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AirportService {

    List<Airport> getByCountryCodeOrName(String code, String name);
}
