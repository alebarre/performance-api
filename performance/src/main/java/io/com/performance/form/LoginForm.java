package io.com.performance.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginForm {

    @NotEmpty(message = "Campo e-mail não pode estar vazio.")
    @Email(message = "E-mail inválido. Por favor, entre com um endereço de e-mail válido.")
    private String email;

    @NotEmpty(message = "Campo senha não pode estar vazio.")
    private String password;

}
