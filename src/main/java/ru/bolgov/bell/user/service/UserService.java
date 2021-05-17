package ru.bolgov.bell.user.service;

import org.springframework.validation.annotation.Validated;
import ru.bolgov.bell.user.dto.UserByIdOutDto;
import ru.bolgov.bell.user.dto.UserByParamInDto;
import ru.bolgov.bell.user.dto.UserByParamOutDto;
import ru.bolgov.bell.user.dto.UserSaveInDto;
import ru.bolgov.bell.user.dto.UserUpdateInDto;

import javax.validation.Valid;
import java.util.List;

/**
 * Сервис для работы с пользователями
 */
@Validated
public interface UserService {
    /**
     * Получить список всех пользователей
     *
     * @return
     */
    List<UserByParamOutDto> allUsers();

    /**
     * Получить список пользователей по настраиваемому фильтру
     *
     * @param paramInDto
     * @return
     */
    List<UserByParamOutDto> usersFilter(@Valid UserByParamInDto paramInDto);

    /**
     * Получить пользователя по ID
     *
     * @param userID
     * @return
     */
    UserByIdOutDto userById(Integer userID);

    /**
     * Обновить данные пользователя
     *
     * @param userIn
     */
    void userUpdate(@Valid UserUpdateInDto userIn);

    /**
     * Сохранить данные нового пользователя
     *
     * @param userIn
     */
    void userSave(@Valid UserSaveInDto userIn);
}
