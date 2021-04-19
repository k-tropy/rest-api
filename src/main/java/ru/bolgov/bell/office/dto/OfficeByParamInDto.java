package ru.bolgov.bell.office.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OfficeByParamInDto implements OfficeDtoWithOrgId{

    @NotNull(message = "org_id cannot be null")
    public Integer orgId;

    @Size(max = 255)
    public String name;

    @Size(max = 16)
    public String phone;

    public Boolean isActive;

    @Override
    public Integer getOrgId() {
        return orgId;
    }
}
