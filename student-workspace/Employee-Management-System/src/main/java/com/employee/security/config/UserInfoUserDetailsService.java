
package com.employee.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.employee.entity.User;
import com.employee.repository.UserRepository;

import java.util.Optional;

/**
 * UserInfoUserDetailsService is responsible for loading user details for authentication and authorization.
 * It interacts with the UserRepository to retrieve user information from the database.
 */
@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;  // Repository for user data access

    /**
     * Loads user details by their username for authentication.
     *
     * @param username The username of the user to load details for
     * @return UserDetails object containing user information
     * @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Retrieve user information from the database by username
        Optional<User> userInfo = repository.findByName(username);

        // Create and return UserDetails object based on retrieved user information
        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}
