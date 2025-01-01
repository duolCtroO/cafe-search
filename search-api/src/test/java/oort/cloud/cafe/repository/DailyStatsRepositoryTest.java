package oort.cloud.cafe.repository;

import jakarta.persistence.EntityManager;
import oort.cloud.cafe.data.StatsResponse;
import oort.cloud.cafe.entity.DailyStats;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


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

    @Test
    void 상위_3개_반환(){
        //given
        LocalDateTime now = LocalDateTime.now();
        DailyStats test1 = new DailyStats("test", now);
        DailyStats test2 = new DailyStats("test", now);
        DailyStats test3 = new DailyStats("test", now);
        DailyStats test4 = new DailyStats("test", now);
        DailyStats test5 = new DailyStats("test", now);

        DailyStats dummy1 = new DailyStats("dummy1", now);
        DailyStats dummy2 = new DailyStats("dummy1", now);
        DailyStats dummy3 = new DailyStats("dummy1", now);
        DailyStats dummy4 = new DailyStats("dummy1", now);

        DailyStats dummy5 = new DailyStats("dummy2", now);
        DailyStats dummy6 = new DailyStats("dummy3", now);
        DailyStats dummy7 = new DailyStats("dummy4", now);

        DailyStats temp1 = new DailyStats("temp", now);
        DailyStats temp2 = new DailyStats("temp", now);
        DailyStats temp3 = new DailyStats("temp", now);

        dailyStatsRepository.saveAll(List.of(test1, test2, test3, test4, test5
            ,dummy1 ,dummy2, dummy3, dummy4, dummy5, dummy6, dummy7
                ,temp1, temp2, temp3
        ));

        //when
        PageRequest page = PageRequest.of(0, 3);
        List<StatsResponse> topQuery = dailyStatsRepository.findTopQuery(page);

        //then
        assertNotNull(topQuery);
        assertEquals(3, topQuery.size());

        assertEquals("test", topQuery.get(0).query());
        assertEquals("dummy1", topQuery.get(1).query());
        assertEquals("temp", topQuery.get(2).query());
    }
}