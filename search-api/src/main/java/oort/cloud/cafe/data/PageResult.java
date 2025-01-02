package oort.cloud.cafe.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(description = "페이징 결과" )
public class PageResult<T> {
    @Schema(description = "페이지 크기", example = "10")
    private int size;
    @Schema(description = "현재 페이지 번호", example = "1")
    private int page;
    @Schema(description = "전체 컨텐츠 크기", example = "100")
    private int total;
    @Schema(description = "본문")
    private List<T> contents;

    public PageResult(int size, int page, int total, List<T> contents) {
        this.size = size;
        this.page = page;
        this.total = total;
        this.contents = contents;
    }
}
