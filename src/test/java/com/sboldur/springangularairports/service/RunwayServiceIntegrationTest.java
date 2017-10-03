package com.sboldur.springangularairports.service;

import com.sboldur.springangularairports.dto.RunwayWithIdentificationsCount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RunwayServiceIntegrationTest {

    @Autowired
    private RunwayService runwayService;

    @Test
    public void shouldFindTop2MostCommonIdentifications() {
        List<RunwayWithIdentificationsCount> runwayWithIdentificationsCount = runwayService.getTopNMostCommonIdentifications(2);

        assertThat(runwayWithIdentificationsCount.size()).isEqualTo(2);
        assertThat(runwayWithIdentificationsCount.get(0).getIdentification()).isEqualTo("H1");
        assertThat(runwayWithIdentificationsCount.get(0).getIdentificationsCount()).isEqualTo(5L);

        assertThat(runwayWithIdentificationsCount.get(1).getIdentification()).isEqualTo("8");
        assertThat(runwayWithIdentificationsCount.get(1).getIdentificationsCount()).isEqualTo(2);
    }

    @Test
    public void shouldReturnRunwaySurfacesByAustriaCountry() {
        List<String> surfaces = runwayService.getRunwaySurfacesByCountry("AT");

        assertThat(surfaces.size()).isEqualTo(3);
        assertThat(surfaces).containsExactlyInAnyOrder("ASPH", "ASPH-G", "TURF");
    }
}
