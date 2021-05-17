package ru.bolgov.bell.user.dto;

import java.util.Date;

/**
 * DTO для передачи результатов поиска пользователя по ID
 */
public class UserByIdOutDto {

    /**
     * ID пользователя
     */
    public Integer id;

    /**
     * Имя пользователя
     */
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
    public String position;

    /**
     * Телефон пользователя
     */
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
     * Название гражданства
     */
    public String citizenshipName;

    /**
     * Код гражданства
     */
    public Integer citizenshipCode;

    /**
     * Метка активности пользователя
     */
    public Boolean isIdentified;
}
