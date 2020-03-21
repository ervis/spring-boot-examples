package webapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContextHolderTests {

    @Test
    @Example
    void createSecurityContextHolderExample() {
        var holder = SecurityContextHolder.createEmptyContext();
        var token = new TestingAuthenticationToken("username", "password", "USER_ROLE");

        holder.setAuthentication(token);

        assertEquals(token, holder.getAuthentication());
    }
}
