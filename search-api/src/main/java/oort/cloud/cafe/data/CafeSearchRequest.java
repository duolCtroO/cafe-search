package oort.cloud.cafe.data;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CafeSearchRequest {
    @NotBlank(message = "query 파라미터는 필수 값 입니다.")
    private String query;

    @Min(value = 1, message = "size는 1이상 10000이하의 값입니다.")
    @Max(value = 10000, message = "size는 1이상 10000이하의 값입니다.")
    @NotNull(message = "size 파라미터는 필수 값 입니다.")
    private Integer size;

    @Min(value = 1, message = "page는 1이상 100이하의 값입니다.")
    @Max(value = 100, message = "page는 1이상 100이하의 값입니다.")
    @NotNull(message = "page 파라미터는 필수 값 입니다.")
    private Integer page;
}
