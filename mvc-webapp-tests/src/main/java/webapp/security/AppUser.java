package webapp.security;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AppUser implements UserDetails {
    private final String username;
    private final Set<GrantedAuthority> permissions;

    AppUser(String username, Set<GrantedAuthority> permissions) {
        this.username = username;
        this.permissions = permissions;
    }

    public static UserDetails create(String username, Set<GrantedAuthority> permissions) {
        return new AppUser(username, Collections.unmodifiableSet(permissions));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
