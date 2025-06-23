package com.clothing.ecommerce.exception.capas;

import com.clothing.ecommerce.exception.AppException;

public class NotDataFoundException extends AppException {

    public NotDataFoundException(String message) {
        super(message);
    }

    public NotDataFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotDataFoundException(Throwable cause) {
        super(cause);
    }

    public NotDataFoundException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NotDataFoundException() {
        super();
    }

    @Override
    public String getLayer() {
        return "service";
    }

}
