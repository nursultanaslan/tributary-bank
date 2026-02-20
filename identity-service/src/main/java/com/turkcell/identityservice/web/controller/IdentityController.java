package com.turkcell.identityservice.web.controller;

import com.turkcell.identityservice.application.usecase.RegisterUserUseCase;
import com.turkcell.identityservice.web.dto.request.RegisterUserRequest;
import com.turkcell.identityservice.web.dto.response.RegisteredUserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/identity")
public class IdentityController {

    private final RegisterUserUseCase registerUserUseCase;

    public IdentityController(RegisterUserUseCase registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegisteredUserResponse register(@RequestBody RegisterUserRequest request) {
        UUID userId = registerUserUseCase.execute(request.email(), request.username(), request.password());
        return new RegisteredUserResponse(userId, request.email(), request.username());
    }
}
