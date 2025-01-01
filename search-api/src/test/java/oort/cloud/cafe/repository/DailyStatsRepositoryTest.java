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

    @Test
    void 쿼리_카운트_조회(){
        //given
        String query = "test";
        LocalDateTime start = LocalDateTime.now().minusMinutes(10);
        LocalDateTime end = LocalDateTime.now();

        DailyStats test1 = new DailyStats(query, LocalDateTime.now().minusMinutes(11));
        DailyStats test2 = new DailyStats(query, LocalDateTime.now().minusMinutes(4));
        DailyStats test3 = new DailyStats(query, LocalDateTime.now().minusMinutes(3));
        DailyStats test4 = new DailyStats(query, LocalDateTime.now().minusMinutes(2));
        DailyStats test5 = new DailyStats(query, LocalDateTime.now().plusMinutes(1));

        //when
        dailyStatsRepository.save(test1);
        dailyStatsRepository.save(test2);
        dailyStatsRepository.save(test3);
        dailyStatsRepository.save(test4);
        dailyStatsRepository.save(test5);

        long count = dailyStatsRepository.countByQueryAndEventDateTimeBetween(query, start, end);

        //then
        Assertions.assertThat(count).isEqualTo(3);
    }
}