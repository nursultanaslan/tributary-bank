package com.turkcell.identityservice.web.controller;

import com.turkcell.identityservice.application.usecase.RegisterUserUseCase;
import com.turkcell.identityservice.web.dto.request.RegisterUserRequest;
import com.turkcell.identityservice.web.dto.response.RegisteredUserResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/identity")
public class IdentityController {

    private final RegisterUserUseCase registerUserUseCase;

    public IdentityController(RegisterUserUseCase registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }

    @GetMapping
    public String getIdentity() {
        return "Identity Service";
    }

    @PostMapping
    public RegisteredUserResponse registerUser(@RequestBody RegisterUserRequest registerUserRequest) {
        return null;
    }
}
