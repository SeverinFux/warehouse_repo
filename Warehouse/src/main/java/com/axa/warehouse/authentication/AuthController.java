package com.axa.warehouse.authentication;

import com.axa.warehouse.authentication.dao.AuthRequest;
import com.axa.warehouse.authentication.dao.AuthResponse;
import com.axa.warehouse.exceptionHandler.BadRequestException;
import com.axa.warehouse.model.User;
import com.axa.warehouse.user.CustomUserDetailsService;
import com.axa.warehouse.user.UserRepository;
import com.axa.warehouse.user.UserService;
import com.axa.warehouse.user.dao.DaoUser;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@CrossOrigin("*")
public class AuthController {


    private final AuthenticationManager authenticationManager;


    private final CustomUserDetailsService userDetailsService;

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadRequestException("Incorrect username or password");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String jwt = JwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }
    @PostMapping("/register")
    public ResponseEntity<?> createAccount(@RequestBody User user){
        DaoUser created = userService.createUser(user);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(created.getUsername());
        final String jwt = JwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}


