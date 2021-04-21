package ru.bolgov.bell.office.dto;

import javax.validation.constraints.Size;

public class OfficeByParamOutDto {

    public Integer id;

    @Size(max = 255)
    public String name;

    public Boolean isActive;
}
