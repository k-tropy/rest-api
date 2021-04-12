package ru.bolgov.bell.organization.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class OrganizationWithoutIdDto {

    @Size(max = 255, message = "Name can not be longer then 255 characters")
    @NotEmpty(message = "Name cannot be null nor empty")
    public String name;

    @Size(max = 255, message = "Full name can not be longer then 255 characters")
    @NotEmpty(message = "Full name cannot be null nor empty")
    public String fullName;

    @Size(min = 10, max = 12, message = "Inn must be between 10 and 12 characters")
    @NotEmpty(message = "Inn cannot be null nor empty")
    public String inn;

    @Size(min = 9, max = 9, message = "Kpp must be 9 characters")
    @NotEmpty(message = "Kpp cannot be null nor empty")
    public String kpp;

    @Size(max = 255, message = "Address can not be longer then 255 characters")
    @NotEmpty(message = "Address cannot be null nor empty")
    public String address;

    @Size(max = 16, message = "Phone can not be longer then 16 characters")
    public String phone;

    public Boolean isActive;
}
