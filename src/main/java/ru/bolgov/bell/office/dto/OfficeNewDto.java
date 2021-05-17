package ru.bolgov.bell.office.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO офис сохранения новых записей
 */
public class OfficeNewDto implements OfficeDtoWithOrgId{

    /**
     * ID организации
     */
    @NotNull(message = "orgId cannot be null")
    public Integer orgId;

    /**
     * Название офиса
     */
    @Size(max = 255)
    public String name;

    /**
     * Адресс офиса
     */
    @Size(max = 255)
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

    @Override
    public Integer getOrgId() {
        return orgId;
    }
}
