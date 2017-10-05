package com.sboldur.springangularairports.repository;

import com.sboldur.springangularairports.dto.RunwayWithIdentificationsCount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RunwayRepositoryIntegrationTest {

    @Autowired
    private RunwayRepository runwayRepository;

    @Test
    public void shouldHaveData() {
        assertThat(runwayRepository.findAll().size()).isEqualTo(15);
    }

    @Test
    public void shouldReturnTop2MostCommonRunwayIdentifications() {
        List<RunwayWithIdentificationsCount> topIdentifications = runwayRepository.findByMostCommonIdentifications(new PageRequest(0, 2));
        assertThat(topIdentifications.size()).isEqualTo(2);

        assertThat(topIdentifications.get(0).getIdentification()).isEqualTo("H1");
        assertThat(topIdentifications.get(0).getIdentificationsCount()).isEqualTo(5);

        assertThat(topIdentifications.get(1).getIdentification()).isEqualTo("8");
        assertThat(topIdentifications.get(1).getIdentificationsCount()).isEqualTo(2);

    }

    @Test
    public void shouldFindDistinctSurfaceByIsoCountry(){
        List<String> distinctSurfaces = runwayRepository.findDistinctRunwaySurfaceByIsoCountry("AT");

        assertThat(distinctSurfaces.size()).isEqualTo(3);
        assertThat(distinctSurfaces).containsExactlyInAnyOrder("ASPH", "ASPH-G", "TURF");
    }
}
