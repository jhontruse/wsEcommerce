package com.clothing.ecommerce.exception.capas;

import com.clothing.ecommerce.exception.AppException;

public class ServiceException extends AppException {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ServiceException() {
        super();
    }

    @Override
    public String getLayer() {
        return "Service";
    }

}
