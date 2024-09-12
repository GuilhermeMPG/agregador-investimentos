package com.example.agregador_investimentos.dto;

import com.example.agregador_investimentos.model.entity.Account;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public record UserDtoResponse(UUID userId, String username, String email,
                              Instant creationTimestamp, Instant updateTimestamp, List<UserAccountDtoResponse> accountList) {
}
