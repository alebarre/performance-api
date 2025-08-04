package io.com.performance.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordForm {

    @NotEmpty(message = "Campo da senha atual não pode estar vazio.")
    private String currentPassword;

    @NotEmpty(message = "Campo da nova senha não pode estar vazio.")
    private String newPassword;

    @NotEmpty(message = "Senhas digitadas não coincidem. Reveja e tente novamente.")
    private String confirmNewPassword;

}
