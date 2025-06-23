package com.clothing.ecommerce.exception.capas;

import com.clothing.ecommerce.exception.AppException;

public class RepositoryException extends AppException {

    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryException(Throwable cause) {
        super(cause);
    }

    public RepositoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public RepositoryException() {
        super();
    }

    @Override
    public String getLayer() {
        return "Repository";
    }

}
