package ru.bolgov.bell.office.service;

import ru.bolgov.bell.office.dto.OfficeByParamInDto;
import ru.bolgov.bell.office.dto.OfficeByParamOutDto;
import ru.bolgov.bell.office.dto.OfficeInfoDto;
import ru.bolgov.bell.office.dto.OfficeNewDto;

import javax.validation.Valid;
import java.util.List;

/**
 * Сервис для работы с офисами
 */
public interface OfficeService {

    /**
     * Получить список офисов по настраиваемому фильтру
     *
     * @param officeInDto
     * @return {@OffOfficeByParamOutDto}
     */
    List<OfficeByParamOutDto> officesFilter (@Valid OfficeByParamInDto officeInDto);

    /**
     * Найти офис по id
     *
     * @param officeId
     * @return {@OfficeInfoDto}
     */
    OfficeInfoDto officeById(Integer officeId);

    /**
     * Обновить информацию о офисе
     *
     * @param officeInDto
     */
    void updateOffice(@Valid OfficeInfoDto officeInDto);

    /**
     * Добавить новый офис
     *
     * @param officeInDto
     */
    void saveOffice(@Valid OfficeNewDto officeInDto);



}
