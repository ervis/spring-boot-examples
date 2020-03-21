package webapp.security;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class Permissions {
    private static final Set<SimpleGrantedAuthority> ROLE_USER = Set.of(
            new SimpleGrantedAuthority("ROLE_USER")
    );

    public static Collection<? extends GrantedAuthority> simpleUser() {
        return Collections.unmodifiableSet(ROLE_USER);
    }
}
