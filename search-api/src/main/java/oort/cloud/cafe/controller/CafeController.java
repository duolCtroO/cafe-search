package oort.cloud.cafe.controller;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import oort.cloud.cafe.data.CafePost;
import oort.cloud.cafe.data.PageResult;
import oort.cloud.cafe.service.CafeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cafe")
public class CafeController {
    private final CafeService cafeService;

    @GetMapping
    public PageResult<CafePost> cafeContents(
            @RequestParam("query") String query,
            @RequestParam("page") int page,
            @RequestParam("size") int size){
        return cafeService.searchContents(query, page, size);
    }
}
