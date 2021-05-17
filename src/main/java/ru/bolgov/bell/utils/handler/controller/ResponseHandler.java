package ru.bolgov.bell.utils.handler.controller;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import ru.bolgov.bell.utils.handler.dto.DataDto;
import ru.bolgov.bell.utils.handler.dto.ExceptionDto;
import ru.bolgov.bell.utils.handler.dto.SuccessDto;

/**
 * Обработчик HTTP ответов
 */
@RestControllerAdvice
public class ResponseHandler implements ResponseBodyAdvice<Object> {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    /**
     * Метод - обёртка результатов перед возвратом их клиенту.
     * - для void методов успешно завершившихся генерируется SuccessDto
     * - ошибки передаются без изменения
     * - остальные результаты помещаются в DataDto
     *
     * @param o
     * @param methodParameter
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (methodParameter.getGenericParameterType().getTypeName().equals("void")) {
            return new SuccessDto();
        }
        if (o instanceof ExceptionDto) {
            return o;
        }

        DataDto dataDto = new DataDto();
        dataDto.data = o;
        return dataDto;
    }
}
