package ru.bolgov.bell.organization.dao;

import ru.bolgov.bell.organization.entity.Organization;

import java.util.List;

/**
 * DAO интерфейс для работы с Organization
 */
public interface OrganizationDao {

    /**
     *
     * @return List<Organization> Возвращает полный список организаций
     */
    List<Organization> all();
}
