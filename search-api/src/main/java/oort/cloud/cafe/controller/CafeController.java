package oort.cloud.cafe.controller;

import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import oort.cloud.cafe.data.CafePost;
import oort.cloud.cafe.data.CafeSearchRequest;
import oort.cloud.cafe.data.PageResult;
import oort.cloud.cafe.service.CafeService;
import org.springframework.validation.annotation.Validated;
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
    public PageResult<CafePost> cafeContents(@Valid CafeSearchRequest cafeSearchRequest){
        return cafeService.searchContents(
                cafeSearchRequest.getQuery()
                , cafeSearchRequest.getPage()
                , cafeSearchRequest.getSize());
    }
}
