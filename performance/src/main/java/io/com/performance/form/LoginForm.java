package io.com.performance.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginForm {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

}
