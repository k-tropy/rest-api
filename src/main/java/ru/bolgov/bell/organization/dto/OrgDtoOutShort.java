package ru.bolgov.bell.organization.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OrgDtoOutShort {
    @NotEmpty
    public Integer id;

    @Size(max = 255)
    @NotEmpty(message = "name cannot be null")
    public String name;

    @NotNull(message = "inn cannot be null")
    @Min(9)
    @Max(12)
    public String inn;

    @Override
    public String toString() {
        return "{id:" + id + ";name:" + name + ";inn:" + inn + "}";
    }
}
