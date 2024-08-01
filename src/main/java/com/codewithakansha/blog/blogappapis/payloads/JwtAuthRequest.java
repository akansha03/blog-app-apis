package com.codewithakansha.blog.blogappapis.payloads;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtAuthRequest {

    private String email;

    private String password;
}
