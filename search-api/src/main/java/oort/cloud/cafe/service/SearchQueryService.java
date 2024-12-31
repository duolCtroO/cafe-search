package oort.cloud.cafe.service;

import lombok.RequiredArgsConstructor;
import oort.cloud.cafe.data.CafePost;
import oort.cloud.cafe.data.PageResult;
import oort.cloud.cafe.repository.CafeRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SearchQueryService implements SearchService{
    private final CafeRepository cafeRepository;

    @Override
    public PageResult<CafePost> search(String query, int size, int page) {
        return cafeRepository.searchContents(query, page, size);
    }
}
