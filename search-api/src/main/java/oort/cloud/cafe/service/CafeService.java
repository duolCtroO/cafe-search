package oort.cloud.cafe.service;

import lombok.RequiredArgsConstructor;
import oort.cloud.cafe.data.CafePost;
import oort.cloud.cafe.data.PageResult;
import oort.cloud.cafe.repository.CafeRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CafeService {
    private final CafeRepository cafeRepository;

    public PageResult<CafePost> searchContents(String query, int page, int size) {
        return cafeRepository.searchContents(query, page, size);
    }
}
