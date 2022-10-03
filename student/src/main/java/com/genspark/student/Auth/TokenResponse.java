package com.genspark.student.Auth;

import java.io.Serializable;

public class TokenResponse implements Serializable {

    private static final long serialVersionUID = 8317676219297719109L;

    private final String token;

    private final String currentUser;

    private final String authorities;

    public TokenResponse(String token, String currentUser,String authorities) {
        this.token = token;
        this.currentUser = currentUser;
        this.authorities = authorities;
    }

    public String getToken() {
        return this.token;
    }

    public String getCurrentUser() {
        return this.currentUser;
    }
    public String getAuthorities() {
        return this.authorities;
    }
    
}
