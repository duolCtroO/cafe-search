package oort.cloud.cafe.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import oort.cloud.cafe.data.CafePost;
import oort.cloud.cafe.data.CafeSearchRequest;
import oort.cloud.cafe.data.PageResult;
import oort.cloud.cafe.data.StatsResponse;
import oort.cloud.cafe.service.CafeApplicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cafe")
public class CafeController {
    private final CafeApplicationService cafeService;

    @GetMapping
    public PageResult<CafePost> cafeContents(@Valid CafeSearchRequest cafeSearchRequest){
        return cafeService.search(
                cafeSearchRequest.getQuery()
                , cafeSearchRequest.getPage()
                , cafeSearchRequest.getSize());
    }

    @GetMapping("/stats")
    public StatsResponse findQueryStats(
                @RequestParam(name = "query") String query,
                @RequestParam(name = "date") LocalDate date
            ){
        return cafeService.findQueryCount(query, date);
    }

    @GetMapping("/stats/top")
    public List<StatsResponse> findQueryTop(){
        return cafeService.findDailyTopQuery();
    }
}
