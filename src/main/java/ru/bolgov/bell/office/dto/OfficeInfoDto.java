package ru.bolgov.bell.office.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OfficeInfoDto{

    @NotNull(message = "id cannot be null")
    public Integer id;

    @Size(max = 255)
    @NotEmpty(message = "Name cannot be null nor empty")
    public String name;

    @Size(max = 255)
    @NotEmpty(message = "Address cannot be null nor empty")
    public String address;

    @Size(max = 16)
    public String phone;

    public Boolean isActive;

}
