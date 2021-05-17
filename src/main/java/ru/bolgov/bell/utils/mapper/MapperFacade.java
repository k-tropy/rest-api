package ru.bolgov.bell.utils.mapper;

import ru.bolgov.bell.office.dto.OfficeDtoWithOrgId;
import ru.bolgov.bell.office.entity.Office;
import ru.bolgov.bell.user.dto.UserByIdOutDto;
import ru.bolgov.bell.user.dto.UserInDto;
import ru.bolgov.bell.user.entity.User;

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
     * Кастомный мапер для преобразования данных из DTO c полем orgId в Office
     * Для DTO реализующих интерфейс OfficeDtoWithOrgId
     *
     * @param sourse
     * @param destinationClass
     * @return
     */
     Office  mapOfficeDtoWhithOrganization(OfficeDtoWithOrgId sourse, Class<Office> destinationClass);

    /**
     * Кастомный маппер для преобразования данны из DTO реалтзующих интерфейс UserInDto
     * в User
     *
     * @param sourse
     * @param destinationClass
     * @return
     */
     User mapUserInDtoToUser(UserInDto sourse, Class<User> destinationClass);

    /**
     * Маппер для преобразования User в DTO для вывода результата
     *
     * @param sourse
     * @param destinationClass
     * @return
     */
     UserByIdOutDto mapUserToOutDto(User sourse, Class<UserByIdOutDto> destinationClass);
}
