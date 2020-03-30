package webapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public DefaultAuthenticationProvider authenticationProvider() {
        return new DefaultAuthenticationProvider();
    }

    @Bean
    public DefaultWebSecurityConfigurerAdapter webSecurityConfigurerAdapter(DefaultAuthenticationProvider provider) {
        return new DefaultWebSecurityConfigurerAdapter(provider);
    }
}
