package com.anterka.saathifund.dto.response;

import com.anterka.saathifund.constants.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private ResponseStatus status;
    private String message;
    private String username;
    private long otpValiditySeconds;
    private LocalDateTime timestamp;
}
