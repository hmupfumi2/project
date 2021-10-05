package com.codevirtus.membershipsystem.usermanagement.user.service.create;

import com.codevirtus.membershipsystem.usermanagement.user.model.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserCommand {

    @NotBlank(message = "First name should be provided")
    private String firstName;

    @NotBlank(message = "Last name should be provided")
    private String lastName;

    private String initials;

    private String title;

    @NotNull(message = "Gender should be provided")
    private Gender gender;

    @NotBlank(message = "Username should be provided")
    private String username;

    @NotBlank(message = "Email should be provided")
    @Email(message = "A valid email should be provided")
    private String email;

    @Min(value = 1, message = "A valid group should be provided")
    private long groupId;

    private String nationalIdentificationNumber;

    private String passportNumber;

    private String driverLicenseNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en_ZW", timezone = "Africa/Harare")
    private Date dateOfBirth;

    private String phoneNumber;

}
