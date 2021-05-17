package ru.bolgov.bell.organization.dto;

import javax.validation.constraints.*;

/**
 * DTO для передачи результатов поиска по фильтру
 */
public class OrganizationByParamOutDto {
    /**
     * ID щрганизации
     */
    @NotEmpty
    public Integer id;

    /**
     * Название организации
     */
    @Size(max = 255)
    @NotEmpty(message = "name cannot be null")
    public String name;

    /**
     * Статус активности организации
     */
    public Boolean isActive;
 }
