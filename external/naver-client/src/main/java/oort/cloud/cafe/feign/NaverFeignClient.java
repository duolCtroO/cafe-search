package oort.cloud.cafe.feign;


import oort.cloud.cafe.feign.config.NaverConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "naverFeingnClient", url = "${external.naver.url}", configuration = NaverConfiguration.class)
public interface NaverFeignClient {
    @GetMapping("/v1/search/cafearticle.json")
    String search(
            @RequestParam("query") String query,
            @RequestParam("start") int start,
            @RequestParam("display") int display
    );
}
