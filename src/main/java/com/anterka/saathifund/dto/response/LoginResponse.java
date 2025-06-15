package com.anterka.saathifund.dto.response;

import com.anterka.saathifund.constants.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private ResponseStatus status;
    private String token;
    private String userId;
    private String username;
    private String email;
}
