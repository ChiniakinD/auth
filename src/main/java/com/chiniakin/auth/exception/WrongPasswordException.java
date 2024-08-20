package com.chiniakin.auth.exception;

/**
 * Исключение, используемое при неверном пароле.
 *
 * @author ChiniakinD
 */
public class WrongPasswordException extends RuntimeException {

    /**
     * @param message сообщение об ошибке.
     */
    public WrongPasswordException(String message) {
        super(message);
    }

}
