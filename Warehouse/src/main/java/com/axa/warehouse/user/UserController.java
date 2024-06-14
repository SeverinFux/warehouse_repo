package com.axa.warehouse.user;

import com.axa.warehouse.model.User;
import com.axa.warehouse.user.dao.DaoUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping()
    List<DaoUser> getAllUser(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    DaoUser getUserById(@PathVariable("id") long id){
        return userService.getUserById(id);
    }
}
