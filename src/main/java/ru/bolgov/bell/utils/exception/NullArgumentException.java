package ru.bolgov.bell.utils.exception;

/**
 * Ошибка генерируется при значениях аргументов публичных методов null или при пустом значении,
 * когда такие значения не допустимы
 */
public class NullArgumentException extends RuntimeException{
    public NullArgumentException() {
        super("The argument in the public method is null.");
    }
}
