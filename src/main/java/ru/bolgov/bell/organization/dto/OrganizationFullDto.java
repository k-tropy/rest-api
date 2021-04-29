package ru.bolgov.bell.organization.dto;

import javax.validation.constraints.NotNull;

/**
 * DTO с полной информацией о организации
 */
public class OrganizationFullDto extends OrganizationWithoutIdDto {
    /**
     * ID организации
     */
    @NotNull
    public Integer id;
}
