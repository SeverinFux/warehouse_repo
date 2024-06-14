package com.axa.warehouse.userprofile;

import com.axa.warehouse.model.User;
import com.axa.warehouse.model.UserProfile;
import com.axa.warehouse.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;

    public List<UserProfile> getAllProfiles() {
      return userProfileRepository.findAll();
    }

    public UserProfile getProfileByName(String name) {
        Optional<User> user = userRepository.findByUsername(name);
        return user.get().getUserProfile();
    }
    public UserProfile updateProfileByName(String name, UserProfile profileIn) {
        Optional<User> user = userRepository.findByUsername(name);
        UserProfile profile = user.get().getUserProfile();
        profile.setFirstName(profileIn.getFirstName());
        profile.setLastName(profileIn.getLastName());
        profile.setAddress(profileIn.getAddress());
        profile.setProfilePictureUrl(profileIn.getProfilePictureUrl());
        userProfileRepository.save(profile);
        return user.get().getUserProfile();
    }
}
