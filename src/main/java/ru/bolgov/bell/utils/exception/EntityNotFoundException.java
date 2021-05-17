package ru.bolgov.bell.utils.exception;

/**
 * Ошибка генерируется при отстутствии результатов поиска
 */
public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String message) {
        super(message);
    }
}
