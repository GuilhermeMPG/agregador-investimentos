package com.example.agregador_investimentos.dto;

import java.util.UUID;

public record UserAccountDtoResponse( UUID accountId, String description, UUID userId, String username) {
}
