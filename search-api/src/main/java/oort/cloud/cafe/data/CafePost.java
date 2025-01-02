package oort.cloud.cafe.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "카페 게시글 정보")
public class CafePost {
    @Schema(description = "게시글 제목")
    private String title;
    @Schema(description = "카페 게시글 링크")
    private String link;
    @Schema(description = "게시글 본문")
    private String description;
    @Schema(description = "카페 이름")
    private String cafeName;
    @Schema(description = "카페 URL")
    private String cafeUrl;

    public CafePost(String title, String link, String description, String cafeName, String cafeUrl) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.cafeName = cafeName;
        this.cafeUrl = cafeUrl;
    }
}
