package com.turkcell.identityservice.web.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterUserRequest(
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @Size(min = 5, max = 100, message = "Email must be between 5 and 100 characters")
        String email,
        @NotBlank(message = "Username is required")
        @Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "Username can only contain letters, numbers, underscores and hyphens")
        @Size(min = 3, max = 50, message = "username must be between 3 and 50 characters")
        String username,
        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 100, message = "password must be at least 8 characters")
        String password) {
}
