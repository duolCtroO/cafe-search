package oort.cloud.cafe.config;

import lombok.RequiredArgsConstructor;
import oort.cloud.cafe.entity.DailyStats;
import oort.cloud.cafe.repository.CafeRepository;
import oort.cloud.cafe.repository.DailyStatsRepository;
import oort.cloud.cafe.service.stats.DailyStatsQueryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ApplicationRunner implements CommandLineRunner {
    private final DailyStatsRepository dailyStatsRepository;

    @Override
    public void run(String... args) throws Exception {
        DailyStats test1 = new DailyStats("test", LocalDateTime.now());
        DailyStats test2 = new DailyStats("test", LocalDateTime.now());
        DailyStats test3 = new DailyStats("test", LocalDateTime.now());
        DailyStats test4 = new DailyStats("test", LocalDateTime.now());
        DailyStats test5 = new DailyStats("test", LocalDateTime.now());

        DailyStats dummy1 = new DailyStats("dummy", LocalDateTime.now());
        DailyStats dummy2 = new DailyStats("dummy", LocalDateTime.now());
        DailyStats dummy3 = new DailyStats("dummy", LocalDateTime.now());
        DailyStats dummy4 = new DailyStats("dummy", LocalDateTime.now());
        DailyStats dummy5 = new DailyStats("dummy", LocalDateTime.now());

        dailyStatsRepository.saveAll(List.of(
                    test1, test2, test3, test4, test5,
                    dummy1, dummy2, dummy3, dummy4, dummy5
                ));
    }
}
