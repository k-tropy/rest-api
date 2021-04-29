package ru.bolgov.bell.office.dto;

import javax.validation.constraints.Size;

/**
 * DTO для передачи результата поиска по параметрам
 */
public class OfficeByParamOutDto {

    /**
     * ID офиса
     */
    public Integer id;

    /**
     * Название офиса
     */
    @Size(max = 255)
    public String name;

    /**
     * Метка активнсоти
     */
    public Boolean isActive;
}
