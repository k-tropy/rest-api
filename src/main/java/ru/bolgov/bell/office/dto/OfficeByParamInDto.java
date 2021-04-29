package ru.bolgov.bell.office.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO с входящими параметрами для поиска
 */
public class OfficeByParamInDto implements OfficeDtoWithOrgId{

    /**
     * ID организации
     */
    @NotNull(message = "org_id cannot be null")
    public Integer orgId;

    /**
     * Название офиса
     */
    @Size(max = 255)
    public String name;

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
