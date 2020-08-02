package webapp;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;

import stability.dto.UserDto;

@SuppressWarnings("PMD")
public final class WiremockFindUserCases {
    private WiremockFindUserCases() {

    }

    public static MappingBuilder userNotFound(String userId) {
        return
                WireMock.get(WireMock.urlEqualTo(String.format("/users/%s", userId)))
                        .willReturn(ResponseDefinitionBuilder.okForEmptyJson().withStatus(HttpStatus.NOT_FOUND.value()));
    }

    // this seems like a generic case
    public static MappingBuilder notFoundWithHtmlBody(String url) {
        return
                WireMock.get(WireMock.urlEqualTo(url))
                        .willReturn(
                                ResponseDefinitionBuilder.responseDefinition()
                                        .withStatus(HttpStatus.NOT_FOUND.value())
                                        .withBody(InvalidResponses.anyHTML())
                        );
    }

    public static MappingBuilder findUser(String userId, UserDto user) {
        return
                WireMock.get(WireMock.urlEqualTo(String.format("/users/%s", userId)))
                        .willReturn(ResponseDefinitionBuilder.okForJson(user));
    }

    // this seems like a generic case
    public static MappingBuilder unauthorized(String url) {
        return
                WireMock.get(WireMock.urlEqualTo(url))
                        .willReturn(ResponseDefinitionBuilder.okForEmptyJson().withStatus(HttpStatus.UNAUTHORIZED.value()));
    }

    public static MappingBuilder okWithXMLBody(String url) {
        return
                WireMock.get(WireMock.urlEqualTo(url))
                        .willReturn(ResponseDefinitionBuilder
                                .responseDefinition()
                                .withStatus(HttpStatus.OK.value())
                                .withBody(InvalidResponses.anyXML())
                        );
    }
}
