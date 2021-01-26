package com.erviszyka.springboot.webmvc;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Value;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class RegisterUserControllerV1 {
    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<Void> createUser(@RequestBody CreateUserRequest request) {
        return handler().apply(request);
    }

    // TODO: Use Either or Try
    private Function<CreateUserRequest, ResponseEntity<Void>> handler() {
        return validateRegisterUserRequest()
                .andThen(deserializeRequest())
                .andThen(checkUserStateConstraints())
                .andThen(createUser())
                .andThen(serializeResponse());
    }

    private Function<CreateUserRequest, CreateUserRequest> validateRegisterUserRequest() {
        return verifyNonNull()
                .andThen(verifyEmailFormat())
                .andThen(verifyPasswordStrength());
    }

    private Function<CreateUserRequest, CreateUserRequest> verifyNonNull() {
        return req -> {
            if (anyNull(req.name, req.email, req.password)) {
                throw new IllegalArgumentException("null value");
            }
            return req;
        };
    }

    private boolean anyNull(Object... objs) {
        return Arrays.stream(objs).anyMatch(Objects::isNull);
    }

    private Function<CreateUserRequest, CreateUserRequest> verifyEmailFormat() {
        return req -> {
            if (!req.email.contains("@")) { // dummy email validations
                throw new IllegalArgumentException("wrong email format");
            }
            return req;
        };
    }

    private Function<CreateUserRequest, CreateUserRequest> verifyPasswordStrength() {
        // can we use Try?
        return req -> {
            if (req.password.length() < 8) { // dummy password strength validations
                throw new IllegalArgumentException("password not strong enough");
            }
            return req;
        };
    }

    private Function<CreateUserRequest, UnverifiedUser> deserializeRequest() {
        return r -> new UnverifiedUser(r.name, r.email, r.password);
    }

    private Function<UnverifiedUser, UnverifiedUser> checkUserStateConstraints() {
        return user -> {
            if (userService.contains(user.email)) {
                throw new IllegalStateException("User already exists");
            }
            return user;
        };
    }

    private Function<UnverifiedUser, User> createUser() {
        // what happens if we to have password as separate argument?
        return userService::create;
    }

    private Function<User, ResponseEntity<Void>> serializeResponse() {
        return r -> ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Value
    static class UnverifiedUser {
        String name;
        String email;
        String password;
    }

    @Value
    static class User {
        String name;
        String email;
    }

    @Value
    static class CreateUserRequest {
        String name;
        String email;
        String password;
    }
}
