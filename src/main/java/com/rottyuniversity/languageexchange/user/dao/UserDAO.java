package com.rottyuniversity.languageexchange.user.dao;

import com.rottyuniversity.languageexchange.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Set;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
public class UserDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String getPasswordByUsername(String username) {
        User user = mongoTemplate.findOne(new Query(where("username").is(username)), User.class);

        return user.getPassword();
    }

    public int createNewUser(String username, String rawPassword, String emailAddress, Set<String> nl, Set<String> ll) {
        if (mongoTemplate.findOne(new Query(where("username").is(username)), User.class) != null) {
            // username exists
            return -1;
        }

        if (mongoTemplate.findOne(new Query(where("emailAddress").is(emailAddress)), User.class) != null) {
            // email address exists
            return -2;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setEmailAddress(emailAddress);
        user.setNativeLanguages(nl);
        user.setLearningLanguages(ll);

        mongoTemplate.insert(user);

        // successfully created a new user
        return 0;
    }

    public int deleteUser(String username) {
        User userToDelete = mongoTemplate.findOne(new Query(where("username").is(username)), User.class);

        if (userToDelete == null) {
            // username does not exist
            return -1;
        }

        boolean isSuccessful = mongoTemplate.remove(userToDelete).getDeletedCount() == 1;

        // successfully created a new user
        return isSuccessful ? 0 : -2;
    }
}
