package com.axa.warehouse.security;

import com.axa.warehouse.authentication.JwtUtil;
import com.axa.warehouse.model.User;
import com.axa.warehouse.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomPermissionEvaluator implements PermissionEvaluator {

    private final UserRepository userRepository;

    @Override
    public boolean hasPermission(Authentication auth, Object targetDomainObject, Object permission) {
        System.out.println("in here");
        if (targetDomainObject instanceof Authentication targetUser) {

            String username = auth.getName();
            User currentUser = userRepository.findByUsername(username).orElse(null);
            User toTargetUser = userRepository.findByUsername(targetUser.getName()).orElse(null);
            return currentUser != null && currentUser.getId().equals(toTargetUser.getId());
        }else return false;

    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        System.out.println("in here - dont be");
        return false;
    }
}
