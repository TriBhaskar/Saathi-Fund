package com.anterka.saathifund.dto.request.register;

import com.anterka.saathifund.constants.KYCStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.anterka.saathifund.entities.SaathiFundUsers}
 * Data Transfer Object to transfer user data between different layers
 * without exposing sensitive entity information
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String phoneNumber;
    private Date dateOfBirth;
    private String address;
    private String city;
    private String state;
    private String pinCode;

    // Helper methods for full name
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
