package com.sboldur.springangularairports.repository;

import com.sboldur.springangularairports.dto.RunwayWithIdentificationsCount;
import com.sboldur.springangularairports.model.Airport;
import com.sboldur.springangularairports.model.Runway;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RunwayRepository extends JpaRepository<Runway, Long> {

    @Query("Select new com.sboldur.springangularairports.dto.RunwayWithIdentificationsCount(leIdent, count(leIdent)) from Runway group by leIdent order by count(leIdent) desc, leIdent asc")
    List<RunwayWithIdentificationsCount> findByMostCommonIdentifications(Pageable pageable);

    @Query("select distinct UPPER(runway.surface) as surface from Runway runway join runway.airport a where a.isoCountry = :isoCountry order by surface asc ")
    List<String> findDistinctRunwaySurfaceByIsoCountry(@Param("isoCountry") String isoCountry);
}
