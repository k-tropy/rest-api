package ru.bolgov.bell.user.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class UserByParamInDto implements UserInDto{

    @NotNull(message = "OfficeId cannot be null")
    public Integer officeId;
    @Size(max = 255)
    public String firstName;
    @Size(max = 255)
    public String secondName;
    @Size(max = 255)
    public String middleName;
    @Size(max = 255)
    public String position;
    public Integer docCode;
    public Integer citizenshipCode;

    @Override
    public Integer getOfficeId() {
        return officeId;
    }

    @Override
    public Integer getDocCode() {
        return docCode;
    }

    @Override
    public Integer getCitizenshipCode() {
        return citizenshipCode;
    }

    //Можно ли в данной ситуации вернуть null?
    @Override
    public String getDocName() {
        return null;
    }

    @Override
    public String getDocNumber() {
        return null;
    }

    @Override
    public Date getDocDate() {
        return null;
    }
}
