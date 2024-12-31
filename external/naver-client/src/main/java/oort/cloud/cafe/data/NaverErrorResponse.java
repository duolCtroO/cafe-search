package oort.cloud.cafe.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NaverErrorResponse {
    private String errorCode;
    private String errorMessage;
    private String desc;
}
