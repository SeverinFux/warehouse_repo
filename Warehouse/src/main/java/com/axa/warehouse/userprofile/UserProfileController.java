package com.axa.warehouse.userprofile;

import com.axa.warehouse.model.UserProfile;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user-profiles")
@CrossOrigin("*")
public class UserProfileController {

    private final UserProfileService userProfileService;


    @GetMapping("/all")
    List<UserProfile> getAllUser() {
        return userProfileService.getAllProfiles();
    }

    @GetMapping
    UserProfile getProfileById(Authentication authentication) {
        return userProfileService.getProfileByName(authentication.getName());
    }

    @PostMapping
    UserProfile updateUserProfileByName(Authentication authentication, @RequestBody UserProfile profileIn){
        System.out.println(profileIn);
        return userProfileService.updateProfileByName(authentication.getName(),profileIn);
    }
}
