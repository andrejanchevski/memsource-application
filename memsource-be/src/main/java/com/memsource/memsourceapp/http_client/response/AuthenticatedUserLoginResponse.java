package com.memsource.memsourceapp.http_client.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticatedUserLoginResponse {

    private AuthenticatedUserDetails user;
    private String token;
    private Object lastInvalidateAllSessionsPerformed;
    private String expires;

}
