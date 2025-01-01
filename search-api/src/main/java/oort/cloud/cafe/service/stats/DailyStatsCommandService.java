package oort.cloud.cafe.service.stats;

import lombok.RequiredArgsConstructor;
import oort.cloud.cafe.entity.DailyStats;
import oort.cloud.cafe.repository.DailyStatsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DailyStatsCommandService {
    private final DailyStatsRepository dailyStatsRepository;

    public void saveSearchStats(DailyStats dailyStats){
        dailyStatsRepository.save(dailyStats);
    }
}
