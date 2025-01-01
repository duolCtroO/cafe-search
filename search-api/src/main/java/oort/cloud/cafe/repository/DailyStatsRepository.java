package oort.cloud.cafe.repository;

import oort.cloud.cafe.data.StatsResponse;
import oort.cloud.cafe.entity.DailyStats;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface DailyStatsRepository extends JpaRepository<DailyStats, Long> {
    long countByQueryAndEventDateTimeBetween(String query, LocalDateTime start, LocalDateTime end);

    @Query("SELECT new oort.cloud.cafe.data.StatsResponse(ds.query, count(ds.query))" +
            "FROM DailyStats ds " +
            "GROUP BY ds.query ORDER BY count(ds.query) DESC")
    List<StatsResponse> findTopQuery(Pageable pageable);
}
