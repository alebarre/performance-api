package io.com.performance.enumeration;


public enum EventType {
    LOGIN_ATTEMPT("Você tentou se logar."),
    LOGIN_ATTEMPT_FAILURE("Você tentou se logar SEM SUCESSO."),
    LOGIN_ATTEMPT_SUCCESS("Você tentou se logar COM SUCESSO."),
    PROFILE_UPDATE("Você atualizou suias informações de perfil."),
    PROFILE_PICTURE_UPDATE("Você atualizou sua foto de perfil"),
    ROLE_UPDATE("Você atualizou suas permissões"),
    ACCOUNT_SETTINGS_UPDATE("Você atualizou configuraçoes da conta"),
    MFA_UPDATE("Você atualizou as configurações de MFA (login em dois fatores)"),
    PASSWORD_UPDATE("Você Atualizou a sua senha.");

    private final String description;

    EventType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
