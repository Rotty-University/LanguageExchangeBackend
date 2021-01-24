package com.rottyuniversity.languageexchange.main.controller;

import com.rottyuniversity.languageexchange.jwt.model.JwtRequest;
import com.rottyuniversity.languageexchange.jwt.model.JwtResponse;
import com.rottyuniversity.languageexchange.jwt.utility.JWTUtility;
import com.rottyuniversity.languageexchange.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String home() {
        return "You are logged in, Chungus God!";
    }

    @PostMapping("/api/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    jwtRequest.getUsername(),
                    jwtRequest.getPassword()
            ));
        } catch (Exception e) {
            throw new Exception("INVALID CREDENTIALS", e);
        }

        final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());

        final String token = jwtUtility.generateToken(userDetails);

        return new JwtResponse(token);
    }

    @GetMapping("/testReq")
    public String testReq(@RequestParam String who) throws Exception {
        return "Hello, " + who;
    }

    @GetMapping("/testPath/{who}")
    public String testPath(@PathVariable String who) throws Exception {
        return "Hello, " + who;
    }

    @GetMapping("/testThree/{who}")
    public String testThree(@PathVariable ("who") String who) throws Exception {
        return "Hello, " + who;
    }
}
