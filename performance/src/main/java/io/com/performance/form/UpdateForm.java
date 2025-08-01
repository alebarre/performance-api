package io.com.performance.form;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateForm {

    @NotNull(message = "ID cannot be Null or Empty")
    private  Long id;

    @Size(min = 3, max = 50, message = "O nome deve conter entre 3 e 50 caracteres.")
    @NotEmpty(message = "Campo nome não pode estar vazio.")
    private String firstName;

    @NotEmpty(message = "Campo último nome não pode estar vazio.")
    private String lastName;

    @NotEmpty(message = "Campo e-mail não pode estar vazio.")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Email inválido"
    )
    private String email;

    @Pattern(regexp = "^\\d{11}$", message = "Campo telefone inválido.")
    private String phone;
    private String address;
    private String title;
    private String bio;

}
