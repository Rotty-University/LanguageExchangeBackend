package com.rottyuniversity.languageexchange.user.dao;

import com.rottyuniversity.languageexchange.repositories.UserRepository;
import com.rottyuniversity.languageexchange.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class UserDAO {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String getPasswordByUsername(String username) {
        User user = userRepository.findByUsername(username);

        return user.getPassword();
    }

    public int createNewUser(String username, String rawPassword, String emailAddress, Set<String> nl, Set<String> ll) {
        if (userRepository.findByUsername(username) != null) {
            // username exists
            return -1;
        }

        if (userRepository.findByEmailAddress(emailAddress) != null) {
            // email address exists
            return -2;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setEmailAddress(emailAddress);
        user.setNativeLanguages(nl);
        user.setLearningLanguages(ll);

        userRepository.save(user);

        // successfully created a new user
        return 0;
    }

    public int deleteUser(String username) {
        User userToDelete = userRepository.findByUsername(username);

        if (userToDelete == null) {
            // username does not exist
            return -1;
        }

        String id = userToDelete.getId();
        userRepository.delete(userToDelete);

        // successfully created a new user
        return userRepository.existsById(id) ? -2 : 0;
    }
}
