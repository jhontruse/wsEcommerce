package com.clothing.ecommerce.exception.capas;

import com.clothing.ecommerce.exception.AppException;

public class DuplicateKeyException extends AppException {

    public DuplicateKeyException(String message) {
        super(message);
    }

    public DuplicateKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateKeyException(Throwable cause) {
        super(cause);
    }

    public DuplicateKeyException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DuplicateKeyException() {
        super();
    }

    @Override
    public String getLayer() {
        return "repository";
    }

}
