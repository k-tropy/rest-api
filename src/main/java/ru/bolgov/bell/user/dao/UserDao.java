package ru.bolgov.bell.user.dao;

import ru.bolgov.bell.user.entity.User;

import java.util.List;

/**
 * DAO интерфейс для работы с User{@link User}
 */
public interface UserDao {

    /**
     * Получить список всех пользователей
     *
     * @return List<User> список сотрудников
     */
    List<User> loadAllUsers();

    /**
     * Получить список сотрудников по заданным параметрам
     *
     * @param userIn известные параметры для поиска
     * @return List<Office> список офисов
     */
    List<User> loadUsersByParam(User userIn, Integer officeId);

    /**
     * Получить пользователя по ID
     *
     * @param userId id пользователя
     * @return пользователь
     */
    User loadUserById(Integer userId);

    /**
     * Обновить информациб об пользователе
     *
     * @param user пользователь
     */
    void updateUser(User user, Integer id, Integer officeId);

    /**
     * Добавление нового пользователя
     *
     * @param user
     */
    void saveUser(User user, Integer officeId);

}
