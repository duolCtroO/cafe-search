package oort.cloud.cafe.data;

import java.util.Date;
import java.util.List;

public record CafeResponse(
        Date lastBuildDate,
        int total,
        int start,
        int display,
        List<CafeItem> items) {
}
