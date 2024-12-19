package oort.cloud.cafe;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

public class NaverClient {
    private final RestClient restClient;
    private final String url;
    private final String clientId;
    private final String clientSecret;

    public NaverClient(
            @Value("external.naver.url")String url,
            @Value("external.naver.headers.client-id")String clientId,
            @Value("external.naver.headers.client-secret")String clientSecret
                       ) {
        this.restClient = RestClient.create();
        this.url = url;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String search(String query){
        return null;
    }


}
