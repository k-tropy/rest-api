package ru.bolgov.bell.user.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class UserUpdateInDto implements UserInDto{

    @NotNull(message = "Id cannot be null")
    public Integer id;
    public Integer officeId;
    @NotEmpty(message = "First name cannot be null or empty")
    public String firstName;
    public String secondName;
    public String middleName;
    @NotEmpty(message = "Position cannot be null or empty")
    public String position;
    @Size(max=16, message = "Phone cannot be longer than 16 chracters")
    public String phone;
    public String docName;
    public String docNumber;
    public Date docDate;
    public Integer citizenshipCode;
    public Boolean isIdentified;

    @Override
    public Integer getOfficeId() {
        return officeId;
    }

    @Override
    public Integer getDocCode() {
        return null;
    }

    @Override
    public String getDocName() {
        return docName;
    }

    @Override
    public String getDocNumber() {
        return docNumber;
    }

    @Override
    public Date getDocDate() {
        return docDate;
    }

    @Override
    public Integer getCitizenshipCode() {
        return citizenshipCode;
    }
}
