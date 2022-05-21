package com.memsource.memsourceapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String name;
    private String userName;
    private String password;
    private Boolean is_active;
    private Boolean isExpired;
    private Boolean isLocked;
    private String authenticationToken;
}
