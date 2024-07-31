package com.chiniakin.auth.exception;

/**
 * Исключение, используемое при некорректных запросах.
 *
 * @author ChiniakinD
 */
public class BadRequestException extends RuntimeException {

    /**
     * @param message сообщение об ошибке.
     */
    public BadRequestException(String message) {
        super(message);
    }

}
