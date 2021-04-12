package ru.bolgov.bell.organization.dto;

import javax.validation.constraints.*;

public class OrganizationByParamOutDto {
    @NotEmpty
    public Integer id;

    @Size(max = 255)
    @NotEmpty(message = "name cannot be null")
    public String name;

    public Boolean isActive;
 }
