package ru.bolgov.bell.organization.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * DTO для получение входящих параметров поиска по фильтру
 */
public class OrganizationByParamInDto {
    /**
     * Название организации - обязательный параметр
     */
    @Size(max = 255)
    @NotEmpty(message = "Name cannot be null or empty")
    public String name;

    /**
     * ИНН организации
     */
    @Size(min = 10, max = 12, message = "Inn must be between than 10 and 12 characters")
    public String inn;

    /**
     * Статус активности организации
     */
    public Boolean isActive;
}
