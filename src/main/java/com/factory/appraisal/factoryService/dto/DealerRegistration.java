package com.factory.appraisal.factoryService.dto;
import com.factory.appraisal.factoryService.Author;
import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * This class is a DTO of Dealer Registration
 */

@Author("Yogesh Kumar V")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DealerRegistration extends Response {

    private Long dealerId;
    private Long mkDealerId;

    @Pattern(regexp = "^(?=[a-zA-Z0-9]{10,30}$)[a-zA-Z0-9]+$")
    @Size(max = 30,min = 12,message = "The User Name should not exceed 30 charcters and it should not be below 12 characters and special characters are not allowed")
    @NotNull
    private String name;
    @Size(max = 15,message = "length must be less than equal to 15 characters")
    @NotNull
    private String firstName;
    @Size(max = 15,message = "length must be less than equal to 15 characters")
    @NotNull
    private String lastName;

    @Size(max = 20)
    @NotNull
    private String aptmentNumber;
    @Size(max = 20,message = "length must be less than equal to 20 characters")
    @NotNull
    private String city;
    @Size(max = 50,message = "length must be less than equal to 50 characters")
    @NotNull
    private String email;
    @Size(max = 20,message = "length must be less than equal to 20 characters")
    @NotNull
    private String password;

    @Size(max=13, min = 10)
    @NotNull
    private String phoneNumber;
    /*@Size(max = 50)
    @NotNull*/
    private String profilePicture;
    @Size(max = 20)

    @NotNull
    private String state;
    @Size(max = 50,message = "length must be less than equal to 50 characters")
    @NotNull
    private String streetAddress;
    @Size(max = 5,message = "length must be less than equal to 5 characters")
    @NotNull
    private String zipCode;

   /* @Size(max = 10,message = "length must be less than equal to 10 characters")
    @NotNull*/
    private String latitude;
/*    @Size(max = 10,message = "length must be less than equal to 10 characters")
    @NotNull*/
    private String longitude;

/*    @Size(max = 50)
    @NotNull*/
    private String taxCertificate;
    @Size(max = 30,message = "length must be less than equal to 30 characters")
/*    @NotNull
    @NotBlank*/
    private String dealershipNames;
/*    @Size(max = 50,message = "length must be less than equal to 50 characters")
    @NotNull*/
    private String dealershipAddress;
/*    @Size(max = 50,message = "length must be less than equal to 50 characters")
    @NotNull*/
    private String dealershipStreet;
/*    @Size(max = 20)
    @NotNull*/
    private String dealershipCity;
/*    @Size(max = 5,message = "length must be less than equal to 5 characters")
    @NotNull*/
    private String dealershipZipCode;
    /*@Pattern(regexp="^\\+1\\s\\(?\\d{3}\\)?[- ]?\\d{3}[- ]?\\d{4}$",
            message = "phone number should be in the format "+
                    "+1-XXX-XXX-XXXX"+"+1XXXXXXXXXX")*/
/*    @Size(max=13, min = 13)
    @NotNull*/
    private String dealershipPhNum;

    /*@Size(max = 50,message = "length must be less than equal to 50 characters")*/
    private String dealerPic;

/*    @NotNull*/
    private Boolean dealerLicense = Boolean.FALSE;

/*    @Size(max = 30,message = "length must be less than equal to 30 characters")
    @NotNull*/
    private String corporationName;

    private String inventoryUrl;

    @NotNull
    private Long roleId;
    private UUID userId;
    private String dealerCert;
    private Long companyId;
    private UUID managerId;
    private UUID factorySalesman;
    private UUID factoryManager;
    private Role roleOfUser;
    private String compName;
    private UUID dealerAdmin;
    private String website;

}
