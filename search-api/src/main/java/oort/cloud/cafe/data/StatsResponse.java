package oort.cloud.cafe.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Schema(description = "통계 응답 데이터")
public record StatsResponse(
        @Schema(description = "조회한 쿼리")
        String query,
        @Schema(description = "조회된 개수")
        long count) {
}
