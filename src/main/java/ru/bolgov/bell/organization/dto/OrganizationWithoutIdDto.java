package ru.bolgov.bell.organization.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
/**
 * DTO с информацией о организации без ID
 */
public class OrganizationWithoutIdDto {

    /**
     * Название организации
     */
    @Size(max = 255, message = "Name can not be longer then 255 characters")
    @NotEmpty(message = "Name cannot be null nor empty")
    public String name;

    /**
     * Полное название организации
     */
    @Size(max = 255, message = "Full name can not be longer then 255 characters")
    @NotEmpty(message = "Full name cannot be null nor empty")
    public String fullName;

    /**
     * ИНН организации
     */
    @Size(min = 10, max = 12, message = "Inn must be between 10 and 12 characters")
    @NotEmpty(message = "Inn cannot be null nor empty")
    public String inn;

    /**
     * КПП организации
     */
    @Size(min = 9, max = 9, message = "Kpp must be 9 characters")
    @NotEmpty(message = "Kpp cannot be null nor empty")
    public String kpp;

    /**
     * Адресс организации
     */
    @Size(max = 255, message = "Address can not be longer then 255 characters")
    @NotEmpty(message = "Address cannot be null nor empty")
    public String address;

    /**
     * Телефон организации
     */
    @Size(max = 16, message = "Phone can not be longer then 16 characters")
    public String phone;

    /**
     * Статус активности организации
     */
    public Boolean isActive;
}
