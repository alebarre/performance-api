package io.com.performance.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateForm {

    @NotNull(message = "ID cannot be Null or Empty")
    private  Long id;

    @NotEmpty(message = "First name cannot be empty")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;

    @NotEmpty(message = "E-mail cannot be empty")
    @Email(message = "Invalid e-mail. Please, enter a valid email address.")
    private String email;

    @Pattern(regexp = "^\\d{11}$", message = "invalid phone number")
    private String phone;
    private String address;
    private String title;
    private String bio;

}
