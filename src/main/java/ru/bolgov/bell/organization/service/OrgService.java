package ru.bolgov.bell.organization.service;

import org.springframework.validation.annotation.Validated;
import ru.bolgov.bell.organization.dto.OrgDtoOutShort;

import java.util.List;

/**
 * Сервис
 */
@Validated
public interface OrgService {

List<OrgDtoOutShort> organization();
}
