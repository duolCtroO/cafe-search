package oort.cloud.cafe.feign;


import oort.cloud.cafe.data.CafeResponse;
import oort.cloud.cafe.feign.config.NaverConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "naverFeingnClient", url = "${external.naver.url}", configuration = NaverConfiguration.class)
public interface NaverFeignClient {
    @GetMapping("/v1/search/cafearticle.json")
    CafeResponse search(
            @RequestParam("query") String query,
            @RequestParam("start") int start,
            @RequestParam("display") int display
    );
}
