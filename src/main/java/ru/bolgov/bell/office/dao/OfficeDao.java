package ru.bolgov.bell.office.dao;

import ru.bolgov.bell.office.entity.Office;
import ru.bolgov.bell.organization.entity.Organization;

import java.util.List;

/**
 * DAO интерфейс для работы с Office{@link Office}
 */
public interface OfficeDao {

    /**
     * Получить список всех офисов
     *
     * @return List<Office> список офисов
     */
    List<Office> loadAllOffices();

    /**
     * Получить список офисов по заданным параметрам
     *
     * @param office известные параметры для поиска
     * @return List<Office> список офисов
     */
    List<Office> loadOfficesByParam(Office office);

    /**
     * Получить офис по ID
     *
     * @param officeId id офиса
     * @return офис
     */
    Office loadOfficeById(Integer officeId);

    /**
     * Обновить информациб об офисе
     *
     * @param office офис
     */
    void updateOffice(Office office);

    /**
     * Добавление нового офиса
     *
     * @param office
     */
    void saveOffice(Office office);



}
