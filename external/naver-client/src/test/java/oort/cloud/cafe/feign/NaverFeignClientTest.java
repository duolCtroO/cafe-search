package oort.cloud.cafe.feign;
import oort.cloud.cafe.data.CafeItem;
import oort.cloud.cafe.data.CafeResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = NaverFeignClientTest.TestConfig.class)
@ActiveProfiles("test")
class NaverFeignClientTest {

    @EnableAutoConfiguration
    @EnableFeignClients(clients = NaverFeignClient.class)
    static class TestConfig{
    }

    @Autowired
    NaverFeignClient naverFeignClient;

    @Test
    void searchTest(){
        //given
        int display = 1;
        int start = 1;
        String query = "test";

        //when
        CafeResponse testResponse = naverFeignClient.search(query, start, 1);

        //then
        assertThat(testResponse.start()).isEqualTo(1);
        assertThat(testResponse.display()).isEqualTo(1);

        List<CafeItem> items = testResponse.items();
        assertThat(items.size()).isEqualTo(1);
        assertThat(items.get(0).getTitle()).contains("test");
    }

}