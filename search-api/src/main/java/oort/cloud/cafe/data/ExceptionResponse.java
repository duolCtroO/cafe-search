package oort.cloud.cafe.data;

import io.swagger.v3.oas.annotations.media.Schema;
import oort.cloud.cafe.exception.ErrorType;

@Schema(description = "에러 응답")
public record ExceptionResponse(
        @Schema(description = "에러 메세지")
        String errorMessage,
        @Schema(description = "에러 유형")
        ErrorType errorType) {
}
