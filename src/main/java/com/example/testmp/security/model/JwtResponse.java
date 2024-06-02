package com.example.testmp.security.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JwtResponse {

    private String type;
    private String token;
    private String username;

}
