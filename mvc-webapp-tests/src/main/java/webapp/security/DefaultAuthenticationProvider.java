package webapp.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class DefaultAuthenticationProvider implements AuthenticationProvider {

    private static final String PRINCIPAL = "login@mail.com";
    private static final String CREDENTIALS = "1234";

    // this can be an http call to an authentication service
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication.getPrincipal() == null || authentication.getCredentials() == null) {
            return null;
        }
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        if (!PRINCIPAL.equals(username) && !CREDENTIALS.equals(password)) {
            throw new UsernameNotFoundException("Username not found");
        }
        return new UsernamePasswordAuthenticationToken(
                PRINCIPAL,
                CREDENTIALS,
                Permissions.simpleUser()
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
