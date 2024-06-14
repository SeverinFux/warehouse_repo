package com.axa.warehouse.authentication.dao;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
