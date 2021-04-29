package ru.bolgov.bell.utils.handler.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bolgov.bell.utils.exception.EntityNotFoundException;
import ru.bolgov.bell.utils.handler.dto.ExceptionDto;
import javax.validation.ValidationException;


/**
 * Обработчик ошибок перед возвращением ответа пользователю
 */
@RestControllerAdvice
public class ExceptionHandlerController{
    private final String SORRY_MESSAGE = "Пожалуйста, попробуйте снова. ";
    private final String VALIDATION_MESSAGE = "Ошибка валидации. Неверный формат вводимых данных.  ";
    private final String INVALID_DATA_MESSAGE = "Запрошенная информация не найдена. ";
    private final String OTHER_ERRORS_MESSAGE = "К сожалению во время работы произошла ошибка. Код ошибки: ";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Обработка ошибок валидации вводимых данных
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ValidationException.class)
    public ExceptionDto validationException(ValidationException e){
        String message = VALIDATION_MESSAGE + SORRY_MESSAGE + e.getMessage();
        return generateResponse(message);
    }

    /**
     * Обработка ошибок сгенерированных при отсутствии результатов поиска в БД
     *
     * @param e
     * @return
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ExceptionDto entityNotFoundException(EntityNotFoundException e) {
        String message = INVALID_DATA_MESSAGE + SORRY_MESSAGE + e.getMessage();
        return generateResponse(message);

    }

    /**
     * Обработка необработанных ошибок. Ошибка логируется и ей присваивается код.
     * На сторону клиента возвращается сообщение с извинениями и кодом ошибки
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ExceptionDto unhandledException(Exception e) {
        String exceptionNumber = "e0001";
        logger.error("Unhandled Exception. ExceptionNumber: [" + exceptionNumber + "]", e);
        String externalMessage = OTHER_ERRORS_MESSAGE + exceptionNumber + " " + SORRY_MESSAGE;
        return generateResponse(externalMessage);

    }

    private ExceptionDto generateResponse(String message){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.error = message;
        return exceptionDto;
    }
}