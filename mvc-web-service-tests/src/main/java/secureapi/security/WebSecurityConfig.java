package secureapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
// We have an explicit configuration for security so that we can add-remove on demand on the tests
public class WebSecurityConfig {

    @Bean
    public DefaultWebSecurityConfigurerAdapter webSecurityConfigurerAdapter() {
        return new DefaultWebSecurityConfigurerAdapter();
    }
}
