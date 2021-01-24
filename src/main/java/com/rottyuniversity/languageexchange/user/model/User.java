package com.rottyuniversity.languageexchange.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    @Id private String id;

    private String username;
    private String password;
    private String emailAddress;

    private Set<String> learningLanguages;
    private Set<String> nativeLanguages;
}
