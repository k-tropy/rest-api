package ru.bolgov.bell.user.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * DTO для получения параметров обновления информации о пользователе
 */
public class UserUpdateInDto implements UserInDto{

    /**
     * ID пользователя
     */
    @NotNull(message = "Id cannot be null")
    public Integer id;

    /**
     * ID офиса пользователя
     */
    public Integer officeId;

    /**
     * Имя пользователя
     */
    @NotEmpty(message = "First name cannot be null or empty")
    public String firstName;

    /**
     * Фамилия пользователя
     */
    public String secondName;

    /**
     * Отчество пользователя
     */
    public String middleName;

    /**
     * Должность пользователя
     */
    @NotEmpty(message = "Position cannot be null or empty")
    public String position;

    /**
     * Телефон пользователя
     */
    @Size(max=16, message = "Phone cannot be longer than 16 chracters")
    public String phone;

    /**
     * Название документа пользователя
     */
    public String docName;

    /**
     * Номер документа пользователя
     */
    public String docNumber;

    /**
     * Дата документа пользователя
     */
    public Date docDate;

    /**
     * Код гражданства
     */
    public Integer citizenshipCode;

    /**
     * Метка идентификации
     */
    public Boolean isIdentified;

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
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDocName() {
        return docName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDocNumber() {
        return docNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date getDocDate() {
        return docDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getCitizenshipCode() {
        return citizenshipCode;
    }
}
