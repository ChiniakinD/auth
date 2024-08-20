package com.chiniakin.auth.exception;

/**
 * Исключение, используемое при проблемах, связанных с авторизацией.
 *
 * @author ChiniakinD
 */
public class NotAuthorizedException extends RuntimeException {

    /**
     * @param message сообщение об ошибке.
     */
    public NotAuthorizedException(String message) {
        super(message);
    }

}
