package com.memsource.memsourceapp.http_client.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticatedUserDetails {

    private String userName;
    private String uid;
    private String id;
    private String firstName;
    private String lastName;
    private String role;
    private String email;
}
