package oort.cloud.cafe;

import oort.cloud.cafe.feign.NaverFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients(clients = NaverFeignClient.class)
public class CafeSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(CafeSearchApplication.class, args);
    }
}
