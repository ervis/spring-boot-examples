package webapp;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.client.WireMockBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class WireMockTest {
    protected WireMock wireMock;

    @BeforeEach
    void setUp() {
        wireMock = new WireMockBuilder()
                .port(8080)
                .http()
                .host("localhost")
                .build();
    }


    @AfterEach
    void tearDown() {
        wireMock.resetScenarios();
        wireMock.resetMappings();
    }
}
