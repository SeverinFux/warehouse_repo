package com.axa.warehouse.authentication.dao;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class AuthResponse {
    private final String jwt;
}
