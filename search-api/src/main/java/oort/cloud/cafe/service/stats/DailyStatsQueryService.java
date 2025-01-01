package oort.cloud.cafe.service.stats;

import lombok.RequiredArgsConstructor;
import oort.cloud.cafe.data.StatsResponse;
import oort.cloud.cafe.repository.DailyStatsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@RequiredArgsConstructor
@Service
public class DailyStatsQueryService {
    private final DailyStatsRepository dailyStatsRepository;

    public StatsResponse searchDailyStats(String query, LocalDate date){
        long count = dailyStatsRepository.countByQueryAndEventDateTimeBetween(
                query,
                LocalDate.now().atStartOfDay(),
                LocalDate.now().atTime(LocalTime.MAX)
        );
        return new StatsResponse(query, date, count);
    }
}
