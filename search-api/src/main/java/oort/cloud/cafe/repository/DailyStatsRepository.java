package oort.cloud.cafe.repository;

import oort.cloud.cafe.entity.DailyStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyStatsRepository extends JpaRepository<DailyStats, Long> {
}
