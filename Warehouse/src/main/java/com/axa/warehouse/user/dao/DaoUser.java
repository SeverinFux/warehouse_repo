package com.axa.warehouse.user.dao;

import com.axa.warehouse.model.Role;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class DaoUser {
    private Long id;
    private String username;
    private List<DaoRole> roles;
}
