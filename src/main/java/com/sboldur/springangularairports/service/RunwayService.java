package com.sboldur.springangularairports.service;

import com.sboldur.springangularairports.dto.RunwayWithIdentificationsCount;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RunwayService {

    List<RunwayWithIdentificationsCount> getTopNMostCommonIdentifications(int topNumber);

    List<String> getRunwaySurfacesByCountry(String countryCode);

}
