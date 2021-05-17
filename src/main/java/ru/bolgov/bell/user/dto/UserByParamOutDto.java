package ru.bolgov.bell.user.dto;

/**
 * DTO для передачи результата поиска пользователей по заданным параметрам
 */
public class UserByParamOutDto {

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
}
