package ru.bolgov.bell.organization.dto;

import javax.validation.constraints.NotNull;

public class OrganizationFullDto extends OrganizationWithoutIdDto {
    @NotNull
    public Integer id;
}
