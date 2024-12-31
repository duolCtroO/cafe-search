package oort.cloud.cafe.repository;

import jakarta.persistence.EntityManager;
import oort.cloud.cafe.entity.DailyStats;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;


@ActiveProfiles("test")
@DataJpaTest
class DailyStatsRepositoryTest {

    @Autowired
    DailyStatsRepository dailyStatsRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    void DB에_저장후_조회(){
        //given
        String query = "test";
        LocalDateTime eventDateTime = LocalDateTime.now();
        DailyStats dailyStats = new DailyStats(query, eventDateTime);

        //when
        DailyStats result = dailyStatsRepository.saveAndFlush(dailyStats);

        //then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isNotNull();

        //and
        entityManager.clear();
        Optional<DailyStats> saveData = dailyStatsRepository.findById(result.getId());
        Assertions.assertThat(saveData.get()).isEqualTo(dailyStats);
    }
}