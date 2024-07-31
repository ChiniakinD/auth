package com.chiniakin.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Глобальный обработчик исключений.
 *
 * @author ChiniakinD
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Обрабатывает исключение {@link AccessDeniedException}.
     *
     * @param e исключение.
     * @return ответ с сообщением об ошибке и код статуса 403.
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> accessDeniedException(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    /**
     * Обрабатывает исключение {@link BadRequestException}.
     *
     * @param e исключение.
     * @return ответ с сообщением об ошибке и код статуса 400.
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> badRequestException(BadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    /**
     * Обрабатывает исключение {@link NotAuthorizedException}.
     *
     * @param e исключение.
     * @return ответ с сообщением об ошибке и код статуса 401.
     */
    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<String> notAuthorizedException(NotAuthorizedException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    /**
     * Обрабатывает исключение {@link UserException}.
     *
     * @param e исключение.
     * @return ответ с сообщением об ошибке и код статуса 400.
     */
    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> userException(UserException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    /**
     * Обрабатывает исключение {@link WrongPasswordException}.
     *
     * @param e исключение.
     * @return ответ с сообщением об ошибке и код статуса 401.
     */
    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<String> wrongPasswordException(WrongPasswordException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

}
