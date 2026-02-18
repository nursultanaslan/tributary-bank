package com.turkcell.identityservice.web.dto.response;

import java.util.UUID;

public record RegisteredUserResponse(UUID userId, String email) {
}
