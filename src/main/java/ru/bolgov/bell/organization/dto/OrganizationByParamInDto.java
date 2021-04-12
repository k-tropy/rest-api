package ru.bolgov.bell.organization.dto;

import javax.validation.constraints.*;

public class OrganizationByParamInDto {
    @Size(max = 255)
    @NotEmpty(message = "name cannot be null")
    public String name;

    @Min(value = 10, message = "Inn must not be shorter than 10 characters" )
    @Max(value = 12, message = "Inn must not be longer than 12 characters")
    public String inn;

    public Boolean isActive;
}
