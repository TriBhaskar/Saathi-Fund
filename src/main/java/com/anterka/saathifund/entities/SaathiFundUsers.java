package com.anterka.saathifund.entities;

import com.anterka.saathifund.constants.KYCStatusEnum;
import com.anterka.saathifund.constants.SaathiFundTables;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = SaathiFundTables.SaathiFundUsers.TABLE_NAME)
public class SaathiFundUsers implements UserDetails, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "saathi_fund_sequence_generator")
    @SequenceGenerator(name = "saathi_fund_sequence_generator", sequenceName = SaathiFundTables.SaathiFundUsers.SEQUENCE_NAME, allocationSize = 1)
    @Column(name = SaathiFundTables.SaathiFundUsers.ID)
    private Long id;

    @Column(name = SaathiFundTables.SaathiFundUsers.FIRST_NAME)
    private String firstName;

    @Column(name = SaathiFundTables.SaathiFundUsers.LAST_NAME)
    private String lastName;

    @Column(name = SaathiFundTables.SaathiFundUsers.USER_NAME)
    private String userName;

    @Column(name = SaathiFundTables.SaathiFundUsers.EMAIL)
    private String email;

    @Column(name = SaathiFundTables.SaathiFundUsers.PASSWORD)
    private String password;

    @Column(name = SaathiFundTables.SaathiFundUsers.PHONE_NUMBER)
    private String phoneNumber;

    @Column(name = SaathiFundTables.SaathiFundUsers.DATE_OF_BIRTH)
    private Date dateOfBirth;

    @Column(name = SaathiFundTables.SaathiFundUsers.ADDRESS)
    private String address;

    @Column(name = SaathiFundTables.SaathiFundUsers.CITY)
    private String city;

    @Column(name = SaathiFundTables.SaathiFundUsers.STATE)
    private String state;

    @Column(name = SaathiFundTables.SaathiFundUsers.PIN_CODE)
    private String pinCode;

    // KYC related fields

    @Column(name = SaathiFundTables.SaathiFundUsers.AADHAR_NUMBER)
    private String aadharNumber;

    @Column(name = SaathiFundTables.SaathiFundUsers.PAN_NUMBER)
    private String panNumber;

    @Column(name = SaathiFundTables.SaathiFundUsers.KYC_STATUS)
    @Enumerated
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private KYCStatusEnum kycStatus;

    // Bank details

    @Column(name = SaathiFundTables.SaathiFundUsers.BANK_NAME)
    private String bankName;

    @Column(name = SaathiFundTables.SaathiFundUsers.BANK_ACCOUNT_NUMBER)
    private String bankAccountNumber;

    @Column(name = SaathiFundTables.SaathiFundUsers.BANK_IFSC_CODE)
    private String bankIfscCode;

    @Column(name = SaathiFundTables.SaathiFundUsers.ACCOUNT_HOLDER_NAME)
    private String accountHolderName;

    // system fields

    @Column(name = SaathiFundTables.SaathiFundUsers.IS_ACTIVE)
    private Boolean isActive = true;

    @Column(name = SaathiFundTables.SaathiFundUsers.EMAIL_VERIFIED)
    private String emailVerified = "false";

    @Column(name = SaathiFundTables.SaathiFundUsers.PHONE_VERIFIED)
    private String phoneVerified = "false";

    @Column(name = SaathiFundTables.SaathiFundUsers.CREATED_AT)
    private Date createdAt;

    @Column(name = SaathiFundTables.SaathiFundUsers.UPDATED_AT)
    private String updatedAt;

    @Column(name = SaathiFundTables.SaathiFundUsers.LAST_LOGIN_AT)
    private String lastLoginAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
