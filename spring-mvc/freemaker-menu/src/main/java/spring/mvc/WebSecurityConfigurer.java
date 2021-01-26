package spring.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import spring.mvc.security.LoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
                   .antMatchers("/", "/assets/**").permitAll()
                   .anyRequest().authenticated()
                   .and()
               .formLogin()
                    .loginPage("/login")
                    .successHandler(new LoginSuccessHandler())
                    .permitAll()
                    .and()
               .logout()
                    .permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withUsername("Ervis Zyka")
                    .password("test")
                    .roles("USER")
                    .build();
        return new InMemoryUserDetailsManager(user);
    }
}
