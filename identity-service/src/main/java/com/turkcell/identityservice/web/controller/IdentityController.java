package com.turkcell.identityservice.web.controller;

import com.turkcell.identityservice.application.usecase.GetUserProfileUseCase;
import com.turkcell.identityservice.application.usecase.RegisterUserUseCase;
import com.turkcell.identityservice.domain.model.User;
import com.turkcell.identityservice.web.dto.request.RegisterUserRequest;
import com.turkcell.identityservice.web.dto.response.RegisteredUserResponse;
import com.turkcell.identityservice.web.dto.response.UserProfileResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/identity")
public class IdentityController {

    private final RegisterUserUseCase registerUserUseCase;
    private final GetUserProfileUseCase getUserProfileUseCase;

    public IdentityController(RegisterUserUseCase registerUserUseCase, GetUserProfileUseCase getUserProfileUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.getUserProfileUseCase = getUserProfileUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegisteredUserResponse register(@RequestBody RegisterUserRequest request) {
        UUID userId = registerUserUseCase.execute(request.email(), request.username(), request.password());
        return new RegisteredUserResponse(userId);
    }

    @GetMapping
    public UserProfileResponse getUserProfile(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String userId = jwt.getSubject();

        User user = getUserProfileUseCase.execute(userId);

        return new UserProfileResponse(
                user.userId(),
                user.email(),
                user.username(),
                user.roles(),
                user.emailVerified()
        );
    }
}
