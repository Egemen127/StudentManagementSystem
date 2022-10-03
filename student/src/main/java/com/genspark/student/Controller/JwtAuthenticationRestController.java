package com.genspark.student.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.genspark.student.Auth.AuthenticationException;
import com.genspark.student.Auth.JwtTokenUtil;
import com.genspark.student.Auth.TokenRequest;
import com.genspark.student.Auth.TokenResponse;
import com.genspark.student.Dao.UserDao;
import com.genspark.student.Entity.SystemUser;

@RestController
@CrossOrigin(origins = "*")
public class JwtAuthenticationRestController {

    @Value("${jwt.http.request.header}")
    private String tokenHeader;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDao userRepo;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SystemUser user) {

        userRepo.save(user);
        return ResponseEntity.ok(user);

    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody TokenRequest authenticationRequest)
            throws Exception {
        System.out.println("getting token");
        System.out.println(authenticationRequest.getUsername() + " " + authenticationRequest.getPassword());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            System.out.println("Incorrect username or password");
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = new User(authenticationRequest.getUsername(), authenticationRequest.getPassword(),
                new ArrayList<>());

        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new TokenResponse(token, userDetails.getUsername(), userRepo.getUserByName(userDetails.getUsername()).getRole().toString()));
    }

    @ExceptionHandler({ AuthenticationException.class })
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

}