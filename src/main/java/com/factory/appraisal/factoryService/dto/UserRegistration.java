package com.factory.appraisal.factoryService.dto;

import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import com.factory.appraisal.factoryService.persistence.model.ERole;
import lombok.*;


import javax.validation.constraints.*;
import java.util.UUID;

/**
 * This class is a DTO of UserRegistration
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRegistration extends Response {


    private UUID id;
    @Size(max = 10)
    private String apartmentNumber;
    @NotNull(message = "city should not be null")
    @Size(max = 20, message = "city length should be 0-10")
    private String city;
    @NotNull(message = "email should not be null")
    @Email(message = "invalid email id")
    @Size(max = 50)
    private String email;
    @NotNull(message = "FirstName should not be null")
    @Size(max = 15 ,message = "maximum size of firstName 15")
    private String firstName;
    @NotNull(message = "LastName should not be null")
    @Size(max = 15,message = "the maximum length is 15 only")
    private String lastName;
    @NotNull(message = "password should not be null")
    @Size(max = 20, message = "the maximum length is 20 only")
    private String password;
    @NotNull(message = "phoneNumber should not be null")
    @Size(max = 13,min = 10, message = "give 13 digit American phone number with +1 code sample example:+1 5551234567")
    private String phoneNumber;
    @NotNull(message = "state should not be null")
    @Size(max = 20 , message = "the maximum size of state is 10 characters only")
    private String state;
    @NotNull(message = "streetAddress should not be null")
    @Size(max = 50,message = "the maximum length is 50 only")
    private String streetAddress;
    @NotNull(message = "zipcode should not be null")
    @Size(max = 5,message = "the maximum length is 5 only")
    private String zipCode;
    @NotNull
    @Pattern(regexp = "^(?=[a-zA-Z0-9]{12,25}$)[a-zA-Z0-9]+$")
    @Size(max = 25,min = 12,message = "The User Name should not exceed 25 characters and it should not be below 12 characters and special characters are not allowed")
    private String userName;
    private Long roleId;
    private Long companyId;
    private UUID managerId;
    private UUID factorySalesman;
    private UUID factoryManager;
    private Role roleOfUser;

    private UUID dealerAdmin;



}
