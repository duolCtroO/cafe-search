package oort.cloud.cafe.data;

import java.time.LocalDate;

public record StatsResponse(String query, LocalDate date, long count) {
}
