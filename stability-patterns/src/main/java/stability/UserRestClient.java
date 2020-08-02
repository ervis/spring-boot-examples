package stability;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import stability.dto.UserDto;

@AllArgsConstructor
public class UserRestClient {
    private final String baseUrl;
    private final RestTemplate testRestTemplate;

    // this implementation doesn't handle any HTTP errors
    // and the errors are propagated to the caller.
    // This is a bad API since errors are not document and can easily change if we upgrade the library
    public UserDto findUserFragileWay(String userId) {
        String url = findUserByIdUrl(userId);
        return testRestTemplate.getForObject(url, UserDto.class);
    }

    /**
     * Find user by id
     *
     * @param userId the user id
     * @return The user wrapped in an optional or throws FindUserException in case of error.
     */
    public Optional<UserDto> findUserById(String userId) {
        // The try-catch approach
        //
        // returns Optional.empty if user not found
        // returns Optional.of(user) in case user is found
        // throws UserFindException in case something goes wrong while retrieving the user (includes timeout)
        String url = findUserByIdUrl(userId);
        try {
            return Optional.ofNullable(testRestTemplate.getForObject(url, UserDto.class));

        } catch (RestClientResponseException e) {
            if (isJSON404Response(e)) {
                return Optional.empty();
            }
            throw new FindUserException(e);
        } catch (Exception e) {
            // catch any unknown exceptions such as Jackson deserialization exceptions
            throw new FindUserException(e);
        }
    }

    // Implement functional Try pattern instead of try-catch
    public Try<Optional<UserDto>> findUserByIdFp(String userId) {
        throw new RuntimeException("TODO");
    }

    private String findUserByIdUrl(String userId) {
        return
                UriComponentsBuilder
                        .fromHttpUrl(baseUrl)
                        .path(String.format("users/%s", userId))
                        .toUriString();
    }

    // NOTE: Using Accept headers doesn't work
    private boolean isJSON404Response(RestClientResponseException e) {
        // TODO check if we can move in a client level
        return
                MediaType.APPLICATION_JSON.equals(e.getResponseHeaders().getContentType())
                        && e.getRawStatusCode() == HttpStatus.NOT_FOUND.value();
    }
}


