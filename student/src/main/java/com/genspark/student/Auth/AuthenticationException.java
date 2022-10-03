package com.genspark.student.Auth;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
        System.out.println("Error");
    }
}
