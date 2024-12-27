package oort.cloud.cafe.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageResult<T> {
    private int size;
    private int page;
    private int total;
    private List<T> contents;

    public PageResult(int size, int page, int total, List<T> contents) {
        this.size = size;
        this.page = page;
        this.total = total;
        this.contents = contents;
    }
}
