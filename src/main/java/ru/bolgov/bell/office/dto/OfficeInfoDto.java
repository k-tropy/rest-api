package ru.bolgov.bell.office.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO c полной информацией о офисе
 */
public class OfficeInfoDto{

    /**
     * ID офиса
     */
    @NotNull(message = "id cannot be null")
    public Integer id;

    /**
     * Название офиса
     */
    @Size(max = 255)
    @NotEmpty(message = "Name cannot be null nor empty")
    public String name;

    /**
     * Адресс офиса
     */
    @Size(max = 255)
    @NotEmpty(message = "Address cannot be null nor empty")
    public String address;

    /**
     * Телефон офиса
     */
    @Size(max = 16)
    public String phone;

    /**
     * Метка активности
     */
    public Boolean isActive;

}
