package oort.cloud.cafe.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class CafePost {
    private String title;
    private String link;
    private String description;
    private String cafeName;
    private String cafeUrl;

    public CafePost(String title, String link, String description, String cafeName, String cafeUrl) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.cafeName = cafeName;
        this.cafeUrl = cafeUrl;
    }
}
