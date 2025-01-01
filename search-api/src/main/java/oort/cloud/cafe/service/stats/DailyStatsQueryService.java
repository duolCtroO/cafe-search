package oort.cloud.cafe.service.stats;

import lombok.RequiredArgsConstructor;
import oort.cloud.cafe.data.StatsResponse;
import oort.cloud.cafe.repository.DailyStatsRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DailyStatsQueryService {
    private final DailyStatsRepository dailyStatsRepository;

    public StatsResponse searchDailyStats(String query, LocalDate date){
        long count = dailyStatsRepository.countByQueryAndEventDateTimeBetween(
                query,
                date.atStartOfDay(),
                date.atTime(LocalTime.MAX)
        );
        return new StatsResponse(query, count);
    }

    //TODO 상위 표현 개수 조정, 특정 쿼리데이터 한에서 조회 등... 추가 요구사항 발생가능...
    public List<StatsResponse> searchDailyTopQuery(){
        return dailyStatsRepository.findTopQuery(PageRequest.of(0, 3));
    }
}
