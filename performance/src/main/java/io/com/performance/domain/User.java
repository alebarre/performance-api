package io.com.performance.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.Collections;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class User {
    private  Long id;

    @NotEmpty(message = "Primeiro nome não pode estar vazio")
    @Size(min = 3, max = 50, message = "O nome deve conter entre 3 e 50 caracteres.")
    private String firstName;

    @NotEmpty(message = "Último nome não pode estar vazio")
    private String lastName;

    @NotEmpty(message = "E-mail não pode estar vazio")
    @Email(message = "E-mail inválido. Por favor reveja e tente novamente.")
    private String email;

    @NotEmpty(message = "Senha não pode estar vazia")
    private String password;
    private String address;
    private String phone;
    private String title;
    private String bio;
    private String imageUrl;
    private boolean enabled;
    private boolean isNotLocked;
    private boolean isUsingMfa;
    private LocalDateTime createdAt;
    private String roleName;
    private String permissions;

}
