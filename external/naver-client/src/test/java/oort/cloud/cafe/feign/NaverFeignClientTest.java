package oort.cloud.cafe.feign;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

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
        String test = naverFeignClient.search("test", 1, 1);
        System.out.println(test);
    }

}