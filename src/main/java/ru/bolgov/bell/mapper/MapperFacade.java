package ru.bolgov.bell.mapper;

import ru.bolgov.bell.office.dto.OfficeByParamInDto;
import ru.bolgov.bell.office.dto.OfficeDtoWithOrgId;
import ru.bolgov.bell.office.entity.Office;

import java.util.List;

/**
 * Фасад для преобразования между можелями БД и фронта
 */
public interface MapperFacade {

    /**
     * Преобразование sourceObject в экземпляр класса destinationClass
     *
     * @param sourceObject     исходный объект
     * @param destinationClass класс, в который надо преобразовать объект
     * @param <S>              тип исходного объекта
     * @param <D>              тип объекта, к который надо преобразовать исходный объект
     * @return экземпляр класса D с данными из sourceObject
     */
    <S, D> D map(S sourceObject, Class<D> destinationClass);

    /**
     * Запись занных из sourceObject в destinationObject
     *
     * @param sourceObject
     * @param destinationObject
     * @param <S>
     * @param <D>
     */
    <S, D> void map(S sourceObject, D destinationObject);

    /**
     * Преобразование коллекции оъектов
     *
     * @param source
     * @param destinationClass
     * @param <S>
     * @param <D>
     * @return
     */
    <S, D> List<D> mapAsList(Iterable<S> source, Class<D> destinationClass);

    /**
     * Кастомный мапер для преобразования данных из DTO c полем orgId в DAO office
     * Для DTO реализующих интерфейс OfficeDtoWithOrgId
     *
     * @param sourse
     * @param destinationClass
     * @return
     */
     Office  mapOfficeDtoWhithOrganization(OfficeDtoWithOrgId sourse, Class<Office> destinationClass);
}
