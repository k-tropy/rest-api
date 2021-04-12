package ru.bolgov.bell.organization.dao;

import ru.bolgov.bell.organization.entity.Organization;

import java.util.List;

/**
 * DAO интерфейс для работы с Organization{@link Organization}
 */
public interface OrganizationDao {

    /**
     * Получить список всех организаций
     *
     * @return List<Organization> список организаций
     */
    List<Organization> loadAllOrganizations();

    /**
     * Получить список организаций по заданным параметрам
     *
     * @param organization известные параметры для поиска
     * @return List<Organization> список организаций
     */
    List<Organization> loadOrganizationsByParam(Organization organization);

    /**
     * Получить организацию по ID
     *
     * @param organizationId id организации
     * @return организация
     */
    Organization loadOrganizationById(Integer organizationId);

    /**
     * Обновление информации о существующей организации
     *
     * @param organization организация
     */
    void updateOrganization(Organization organization);

    /**
     * Добавление новой организации
     *
     * @param organization организация
     */
    void saveOrganization(Organization organization);




}
