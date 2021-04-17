package ru.bolgov.bell.organization.service;

import org.springframework.validation.annotation.Validated;
import ru.bolgov.bell.organization.dto.OrganizationByParamInDto;
import ru.bolgov.bell.organization.dto.OrganizationByParamOutDto;
import ru.bolgov.bell.organization.dto.OrganizationFullDto;
import ru.bolgov.bell.organization.dto.OrganizationWithoutIdDto;

import javax.validation.Valid;
import java.util.List;

/**
 * Сервис для работы с организациями
 */
@Validated
public interface OrganizationService {

    /**
     * Получить список всех организаций
     *
     * @return {@OrganizationFullDto}
     */
    List<OrganizationFullDto> organizations();

    /**
     * Получить список организаций по настраиваемому фильтру
     *
     * @param organizationIn
     * @return {@OrganizationByParamOutDto}
     */
    List<OrganizationByParamOutDto> organizationsFilter(@Valid OrganizationByParamInDto organizationIn);

    /**
     * Найти организацию по ID
     *
     * @param organizationId
     * @return {@OrganizationFullDto}
     */
    OrganizationFullDto organizationById(Integer organizationId);

    /**
     * Обновить информацию о организации
     *
     * @param organizationIn
     */
    void updateOrganization(@Valid OrganizationFullDto organizationIn);

    /**
     * Добавить новую организацию
     *
     * @param organizationIn
     */
    void saveOrganization(@Valid OrganizationWithoutIdDto organizationIn);
}
