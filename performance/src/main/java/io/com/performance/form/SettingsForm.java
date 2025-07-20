package io.com.performance.form;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettingsForm {

    @NotNull(message = "Enabled cannot be empty")
    private Boolean enabled;

    @NotNull(message = "Not locked cannot be empty")
    private Boolean notLocked;

}
