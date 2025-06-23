package com.clothing.ecommerce.exception.capas;

import com.clothing.ecommerce.exception.AppException;

public class ControllerException extends AppException {

    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ControllerException(Throwable cause) {
        super(cause);
    }

    public ControllerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ControllerException() {
        super();
    }

    @Override
    public String getLayer() {
        return "Controller";
    }

}
