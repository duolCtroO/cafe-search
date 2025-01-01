package oort.cloud.cafe.service.stats;

import oort.cloud.cafe.data.StatsResponse;
import oort.cloud.cafe.repository.DailyStatsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DailyStatsQueryServiceTest {
    private DailyStatsQueryService dailyStatsQueryService;
    private DailyStatsRepository dailyStatsRepository;

    @BeforeEach
    void init(){
        dailyStatsRepository = mock(DailyStatsRepository.class);
        dailyStatsQueryService = new DailyStatsQueryService(dailyStatsRepository);
    }

    @Test
    void 데일리_통계_조회_날짜_데이터_확인(){
        //given
        String query = "test";
        LocalDate date = LocalDate.of(2025, 1, 1);
        LocalDateTime startDate = date.atStartOfDay();
        LocalDateTime endDate = date.atTime(LocalTime.MAX);

        //when
        when(dailyStatsRepository.countByQueryAndEventDateTimeBetween(query, startDate, endDate)).thenReturn(10L);
        dailyStatsQueryService.searchDailyStats(query, date);

        ArgumentCaptor<String> queryCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<LocalDateTime> startDateCaptor = ArgumentCaptor.forClass(LocalDateTime.class);
        ArgumentCaptor<LocalDateTime> endDateCaptor = ArgumentCaptor.forClass(LocalDateTime.class);

        verify(dailyStatsRepository).countByQueryAndEventDateTimeBetween(
                queryCaptor.capture(),
                startDateCaptor.capture(),
                endDateCaptor.capture()
        );

        //then
        assertEquals(
                LocalDateTime.of(2025, 1, 1, 0, 0, 0)
                ,startDateCaptor.getValue().withNano(0));
        assertEquals(
                LocalDateTime.of(2025, 1, 1, 23, 59, 59)
                ,endDateCaptor.getValue().withNano(0)
        );

    }
}