package secureapi.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

public class DefaultWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

    public DefaultWebSecurityConfigurerAdapter(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/version").permitAll()
                .anyRequest().authenticated();
    }

    @Override
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }
}