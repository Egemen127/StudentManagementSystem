package com.genspark.student.Auth;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenRequest implements Serializable {
    private static final long serialVersionUID = -5616176897013108345L;

    private String username;
    private String password;

    public TokenRequest() {
        super();
    }

    public TokenRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
