package webapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import stability.FindUserException;
import stability.UserRestClient;
import stability.config.UserClientConfig;
import stability.dto.UserDto;

@ContextConfiguration(classes = {
        UserClientConfig.class
})
@SpringBootTest
public class UserClientTest extends WireMockTest {
    private String userId;

    @Autowired
    UserRestClient client;

    @Override
    @BeforeEach
    void setUp() {
        super.setUp();
        userId = UUID.randomUUID().toString();
    }

    @Test
    void testSUTNotNull() {
        assertNotNull(client);
    }

    @Test
    @DisplayName("handles gracefully the case where server responds with XML instead of JSON and JSON deserialization fails")
    void testHandlesGracefullyTheCaseWhereServerRespondsWithXmlInsteadOfJson() {
        String url = String.format("/users/%s", userId);
        wireMock.register(WiremockFindUserCases.okWithXMLBody(url));

        assertThrows(FindUserException.class, () -> client.findUserById(userId));
    }

    @Test
    @DisplayName("handles gracefully the case where the client has invalid credentials to call the API")
    void testCaseWithClientHavingInvalidCredentialsToCallTheAPI() {
        String url = String.format("/users/%s", userId);
        wireMock.register(WiremockFindUserCases.unauthorized(url));

        assertThrows(FindUserException.class, () -> client.findUserById(userId));
    }


    @Test
    @DisplayName("finds user by id")
    void testCaseWithUserReturnedCorrectly() {
        UserDto user = UserFixtures.johnDoe();
        wireMock.register(WiremockFindUserCases.findUser(userId, user));

        UserDto response = client.findUserById(userId).orElseThrow(() -> new AssertionFailedError("mocking didn't work"));

        assertEquals(user.getName(), response.getName());
        assertEquals(user.getEmail(), response.getEmail());
    }

    @Test
    @DisplayName("user with id is not found")
    void testCaseWhereUserIsNotFound() {
        wireMock.register(WiremockFindUserCases.userNotFound(userId));
        assertTrue(client.findUserById(userId).isEmpty());
    }

    @Test
    @DisplayName("user with id is not found but content type is not JSON")
    void testCaseWhereUserIsNotFoundButContentTypeIsNotJSON() {
        wireMock.register(WiremockFindUserCases.notFoundWithHtmlBody(String.format("/users/%s", userId)));
        assertThrows(FindUserException.class, () -> client.findUserById(userId));
    }
}
