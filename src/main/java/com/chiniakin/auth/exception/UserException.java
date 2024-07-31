package com.chiniakin.auth.exception;

/**
 * Исключение, используемое при некорректных запросах, связанных с пользователем.
 *
 * @author ChiniakinD
 */
public class UserException extends RuntimeException {

    /**
     * @param message сообщение об ошибке.
     */
    public UserException(String message) {
        super(message);
    }

}
