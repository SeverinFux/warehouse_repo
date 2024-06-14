package com.axa.warehouse.user;

import com.axa.warehouse.exceptionHandler.BadRequestException;
import com.axa.warehouse.exceptionHandler.NotFoundException;
import com.axa.warehouse.model.Role;
import com.axa.warehouse.model.User;
import com.axa.warehouse.model.UserProfile;
import com.axa.warehouse.repository.RoleRepository;
import com.axa.warehouse.user.dao.DaoRole;
import com.axa.warehouse.user.dao.DaoUser;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;


    @Secured("ROLE_ADMIN")
    public List<DaoUser> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::dalToDaoUser)
                .collect(Collectors.toList());
    }
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasPermission(authentication,#id)")
    public DaoUser getUserById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id: " + id + " not found"));
        return dalToDaoUser(user);
    }
    public DaoUser createUser(User user) {
        if(userRepository.findByUsername(user.getUsername()).isEmpty()) {
            //Default user
            Role role = roleRepository.getRoleByName("ROLE_USER").get();
            user.setRoles(List.of(role));
            user.setUserProfile(new UserProfile());
            User createdUser = userRepository.save(user);
            return dalToDaoUser(createdUser);
        }else throw new BadRequestException("User name already used");
    }
    private DaoUser dalToDaoUser(User user) {
        return DaoUser.builder()
                .id(user.getId())
                .username(user.getUsername())
                .roles(user.getRoles().stream()
                        .map(role -> new DaoRole(role.getId(), role.getName())).collect(Collectors.toList()))
                .build();
    }


}
