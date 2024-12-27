package oort.cloud.cafe.repository;

import lombok.AllArgsConstructor;
import oort.cloud.cafe.data.CafePost;
import oort.cloud.cafe.data.CafeResponse;
import oort.cloud.cafe.data.PageResult;
import oort.cloud.cafe.feign.NaverFeignClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class NaverCafeRepository implements CafeRepository{
    private final NaverFeignClient naverFeignClient;

    @Override
    public PageResult<CafePost> searchContents(String query, int page, int size) {
        CafeResponse response = naverFeignClient.search(query, page, size);
        List<CafePost> cafePosts = response.items().stream()
                .map(m -> new CafePost(
                        m.getTitle(),
                        m.getLink(),
                        m.getDescription(),
                        m.getCafename(),
                        m.getCafeurl()
                )).collect(Collectors.toList());
        return new PageResult<>(size, page, response.total(), cafePosts);
    }
}
