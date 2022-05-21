package com.memsource.memsourceapp.http_client.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthenticateUserRequest {
    @NonNull
    private String userName;
    @NonNull
    private String password;
}
