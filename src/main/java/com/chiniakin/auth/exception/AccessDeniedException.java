package com.chiniakin.auth.exception;

/**
 * Исключение о недостатке прав доступа.
 *
 * @author ChiniakinD
 */
public class AccessDeniedException extends RuntimeException {

    /**
     * @param message сообщение об ошибке.
     */
    public AccessDeniedException(String message) {
        super(message);
    }

}
