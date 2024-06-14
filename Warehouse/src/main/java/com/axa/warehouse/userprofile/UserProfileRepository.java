package com.axa.warehouse.userprofile;

import com.axa.warehouse.model.User;
import com.axa.warehouse.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}