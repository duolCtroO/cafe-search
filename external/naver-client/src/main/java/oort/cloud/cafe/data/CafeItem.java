package oort.cloud.cafe.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CafeItem {
    private String title;
    private String link;
    private String description;
    private String cafename;
    private String cafeurl;
}
