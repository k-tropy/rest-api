package ru.bolgov.bell.user.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class UserSaveInDto implements UserInDto{

    @NotNull(message = "OfficeId cannot be null")
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
    public Integer docCode;
    public Date docDate;
    public Integer citizenshipCode;
    public Boolean isIdentified;

    @Override
    public Integer getOfficeId() {
        return officeId;
    }

    @Override
    public Integer getDocCode() {
        System.out.println("Вызвали DocCode в правильном месте");
        return docCode;
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

    @Override
    public String toString() {
        return "UserSaveInDto{" +
                "officeId=" + officeId +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", position='" + position + '\'' +
                ", phone='" + phone + '\'' +
                ", docName='" + docName + '\'' +
                ", docNumber='" + docNumber + '\'' +
                ", docCode=" + docCode +
                ", docDate=" + docDate +
                ", citizenshipCode=" + citizenshipCode +
                ", isIdentified=" + isIdentified +
                '}';
    }
}
