package oort.cloud.cafe.repository;

import oort.cloud.cafe.data.CafePost;
import oort.cloud.cafe.data.PageResult;
import org.springframework.stereotype.Repository;

public interface CafeRepository {
    PageResult<CafePost> searchContents(String query, int page, int size);
}
