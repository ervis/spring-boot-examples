package secureapi;

import java.time.Clock;

import org.springframework.context.annotation.Bean;

public class AppConfig {
    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }
}
