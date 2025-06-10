package io.com.performance.excecption;

public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }

}
