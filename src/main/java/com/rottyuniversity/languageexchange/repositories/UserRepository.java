package com.rottyuniversity.languageexchange.repositories;

import com.rottyuniversity.languageexchange.user.model.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface UserRepository extends CrudRepository<User, String> {
    User findByUsername(String username);

    User findByEmailAddress(String emailAddress);
}
