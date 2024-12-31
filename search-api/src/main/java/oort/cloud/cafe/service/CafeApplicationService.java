package oort.cloud.cafe.service;

import lombok.RequiredArgsConstructor;
import oort.cloud.cafe.data.CafePost;
import oort.cloud.cafe.data.PageResult;
import oort.cloud.cafe.entity.DailyStats;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Primary
@RequiredArgsConstructor
public class CafeApplicationService implements CafeService{
    private final SearchService cafeService;
    private final DailyStatsCommandService dailyStatsCommandService;

    public PageResult<CafePost> search(String query, int size, int page){
        PageResult<CafePost> search = cafeService.search(query, size, page);
        DailyStats dailyStats = new DailyStats(query, LocalDateTime.now());
        dailyStatsCommandService.saveSearchStats(dailyStats);
        return search;
    }
}
