package com.axa.warehouse.user.dao;

import com.axa.warehouse.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@AllArgsConstructor
public class DaoRole {
    private Long id;
    private String roleName;
}
