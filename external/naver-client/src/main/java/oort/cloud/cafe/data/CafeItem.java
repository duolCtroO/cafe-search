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

    public CafeItem(String title, String link, String description, String cafename, String cafeurl) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.cafename = cafename;
        this.cafeurl = cafeurl;
    }
}
