package oort.cloud.cafe.repository;

import oort.cloud.cafe.entity.DailyStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface DailyStatsRepository extends JpaRepository<DailyStats, Long> {
    long countByQueryAndEventDateTimeBetween(String query, LocalDateTime start, LocalDateTime end);
}
