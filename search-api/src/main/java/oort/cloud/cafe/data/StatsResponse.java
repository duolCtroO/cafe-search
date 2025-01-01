package oort.cloud.cafe.data;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

public record StatsResponse(String query, long count) {
}
