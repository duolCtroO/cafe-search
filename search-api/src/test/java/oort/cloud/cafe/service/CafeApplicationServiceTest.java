package oort.cloud.cafe.service;

import oort.cloud.cafe.data.StatsResponse;
import oort.cloud.cafe.entity.DailyStats;
import oort.cloud.cafe.service.search.SearchQueryService;
import oort.cloud.cafe.service.stats.DailyStatsCommandService;
import oort.cloud.cafe.service.stats.DailyStatsQueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class CafeApplicationServiceTest {
    CafeApplicationService cafeApplicationService;
    DailyStatsCommandService dailyStatsCommandService;
    SearchQueryService cafeQueryService;

    DailyStatsQueryService dailyStatsQueryService;

    @BeforeEach
    void init(){
        cafeQueryService = mock(SearchQueryService.class);
        dailyStatsCommandService = mock(DailyStatsCommandService.class);
        dailyStatsQueryService = mock(DailyStatsQueryService.class);
        cafeApplicationService = new CafeApplicationService(cafeQueryService, dailyStatsCommandService, dailyStatsQueryService);
    }

    @Test
    void API요청후_통계데이터_저장(){
        //given
        String query = "test";
        int size = 2;
        int page = 1;

        cafeApplicationService.search(query, size, page);

        ArgumentCaptor<String> queryCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> sizeCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> pageCaptor = ArgumentCaptor.forClass(Integer.class);

        verify(cafeQueryService, times(1)).search(queryCaptor.capture()
                , sizeCaptor.capture()
                , pageCaptor.capture());

        assertEquals(query, queryCaptor.getValue());
        assertEquals(size, sizeCaptor.getValue());
        assertEquals(page, pageCaptor.getValue());

        ArgumentCaptor<DailyStats> dailyStatsArgumentCaptor = ArgumentCaptor.forClass(DailyStats.class);
        verify(dailyStatsCommandService, times(1)).saveSearchStats(dailyStatsArgumentCaptor.capture());

        DailyStats resultStats = dailyStatsArgumentCaptor.getValue();
        assertEquals(query, resultStats.getQuery());
    }

    @Test
    void 쿼리_통계데이터_조회(){
        //given
        String query = "test";
        LocalDate date = LocalDate.now();

        cafeApplicationService.findQueryCount(query, date);

        ArgumentCaptor<String> queryCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<LocalDate> dateCaptor = ArgumentCaptor.forClass(LocalDate.class);

        verify(dailyStatsQueryService, times(1)).searchDailyStats(
                queryCaptor.capture()
                ,dateCaptor.capture()
        );

        assertEquals(query, queryCaptor.getValue());
        assertEquals(date, dateCaptor.getValue());
    }
}