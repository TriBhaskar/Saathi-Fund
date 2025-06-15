package com.anterka.saathifund.dto.request.login;

import com.anterka.saathifund.constants.IdentifierEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private IdentifierEnum identifierType; // Assuming IdentifierEnum is an enum with EMAIL and USERNAME
    private String username;
    private String password;
}
