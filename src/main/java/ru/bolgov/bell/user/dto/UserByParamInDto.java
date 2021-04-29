package ru.bolgov.bell.user.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * DTO для получения параметров поиска
 */
public class UserByParamInDto implements UserInDto{

    /**
     * ID офиса
     */
    @NotNull(message = "OfficeId cannot be null")
    public Integer officeId;

    /**
     * Имя пользователя
     */
    @Size(max = 255)
    public String firstName;

    /**
     * Фамилия пользователя
     */
    @Size(max = 255)
    public String secondName;

    /**
     * Отчество пользователя
     */
    @Size(max = 255)
    public String middleName;

    /**
     * Должность пользователя
     */
    @Size(max = 255)
    public String position;

    /**
     * Код документа пользователя
     */
    public Integer docCode;

    /**
     * Код гражданства
     */
    public Integer citizenshipCode;

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getOfficeId() {
        return officeId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getDocCode() {
        return docCode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getCitizenshipCode() {
        return citizenshipCode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDocName() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDocNumber() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date getDocDate() {
        return null;
    }
}
