package oort.cloud.cafe.service;

import lombok.RequiredArgsConstructor;
import oort.cloud.cafe.data.CafePost;
import oort.cloud.cafe.data.PageResult;
import oort.cloud.cafe.data.StatsResponse;
import oort.cloud.cafe.entity.DailyStats;
import oort.cloud.cafe.service.search.SearchService;
import oort.cloud.cafe.service.stats.DailyStatsCommandService;
import oort.cloud.cafe.service.stats.DailyStatsQueryService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Primary
@RequiredArgsConstructor
public class CafeApplicationService{
    private final SearchService cafeService;
    private final DailyStatsCommandService dailyStatsCommandService;

    private final DailyStatsQueryService dailyStatsQueryService;

    public PageResult<CafePost> search(String query, int size, int page){
        PageResult<CafePost> search = cafeService.search(query, size, page);
        DailyStats dailyStats = new DailyStats(query, LocalDateTime.now());
        dailyStatsCommandService.saveSearchStats(dailyStats);
        return search;
    }

    public StatsResponse findQueryCount(String query, LocalDate date){
        return dailyStatsQueryService.searchDailyStats(query, date);
    }


}
