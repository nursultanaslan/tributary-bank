package com.turkcell.identityservice.web.dto.response;

import java.util.List;
import java.util.UUID;

public record UserProfileResponse(
        UUID userId,
        String email,
        String username,
        List<String> roles,
        Boolean emailVerified
) {
}
