package stability.config;

import java.util.Collections;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import stability.UserRestClient;

@Configuration
public class UserClientConfig {
    @Bean
    public UserRestClient userRestClient(RestTemplate restTemplate) {
        return new UserRestClient("http://localhost:8080/", restTemplate);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.setMessageConverters(Collections.singletonList(new MappingJackson2HttpMessageConverter(objectMapper())));

        return restTemplate;
    }

    private ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
