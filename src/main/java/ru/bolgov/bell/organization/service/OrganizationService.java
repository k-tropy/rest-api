package ru.bolgov.bell.organization.service;

import org.springframework.validation.annotation.Validated;
import ru.bolgov.bell.organization.dto.OrganizationFullDto;

import java.util.List;

/**
 * Сервис
 */
@Validated
public interface OrganizationService {

List<OrganizationFullDto> organization();
}
