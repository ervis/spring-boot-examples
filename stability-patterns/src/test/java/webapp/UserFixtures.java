package webapp;

import stability.dto.UserDto;

@SuppressWarnings("PMD")
public final class UserFixtures {
    private UserFixtures() {

    }

    public static UserDto johnDoe() {
        return new UserDto("John Doe", "me@erviszyka.com");
    }
}
