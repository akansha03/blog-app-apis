package com.codewithakansha.blog.blogappapis.payloads;

import java.util.Set;
import java.util.HashSet;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private Integer id;

    @NotEmpty
    @Size(min = 4, message = "Username should be more than 4 characters")
    private String username;

    @NotEmpty
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 3, max = 10, message ="Password should be more than 3 characters")
    private String password;

    @Email(message = "Email Address is not valid")
    private String email;

    @NotEmpty
    @Size(min = 10, max = 10, message = "Phone number should be on 10 numbers")
    private String phone;

    @NotEmpty
    private String about;

    private Set<RoleDto> roles = new HashSet<>();
}
