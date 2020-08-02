package stability;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindUserException extends RuntimeException {
    private final Exception cause;
}
