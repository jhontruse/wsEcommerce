package com.clothing.ecommerce.exception.capas;

import com.clothing.ecommerce.exception.AppException;

public class ValidationException extends AppException {

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

    public ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ValidationException() {
        super();
    }

    @Override
    public String getLayer() {
        return "Validation";
    }

}
