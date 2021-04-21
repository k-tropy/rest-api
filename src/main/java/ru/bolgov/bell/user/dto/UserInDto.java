package ru.bolgov.bell.user.dto;

import java.util.Date;

public interface UserInDto {

    Integer getOfficeId();
    Integer getDocCode();
    String getDocName();
    String getDocNumber();
    Date getDocDate();
    Integer getCitizenshipCode();
}
