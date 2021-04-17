package ru.bolgov.bell.organization.dto;

import javax.validation.constraints.*;

public class OrganizationByParamInDto {
    @Size(max = 255)
    @NotEmpty(message = "name cannot be null")
    public String name;

    @Size(min = 10, max = 12, message = "Inn must be between than 10 and 12 characters")
    public String inn;

    public Boolean isActive;
}
