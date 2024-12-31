package oort.cloud.cafe.data;

import oort.cloud.cafe.exception.ErrorType;

public record ExceptionResponse(String errorMessage, ErrorType errorType) {
}
