package oort.cloud.cafe.service;

import oort.cloud.cafe.data.CafePost;
import oort.cloud.cafe.data.PageResult;

public interface SearchService {
    PageResult<CafePost> search(String query, int size, int page);
}