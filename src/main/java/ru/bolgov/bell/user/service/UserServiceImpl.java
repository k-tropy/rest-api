package ru.bolgov.bell.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bolgov.bell.mapper.MapperFacade;
import ru.bolgov.bell.user.dao.UserDao;
import ru.bolgov.bell.user.dto.*;
import ru.bolgov.bell.user.entity.User;

import javax.validation.Valid;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class UserServiceImpl implements UserService{

    private final UserDao dao;
    private final MapperFacade mapper;

    @Autowired
    public UserServiceImpl(UserDao dao, MapperFacade mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<UserByParamOutDto> allUsers() {
        List<User> users = dao.loadAllUsers();
        return mapper.mapAsList(users, UserByParamOutDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<UserByParamOutDto> usersFilter(@Valid UserByParamInDto filterInDto) {
        User parameters = mapper.mapUserInDtoToUser(filterInDto, User.class);
        List<User> users = dao.loadUsersByParam(parameters);
        return mapper.mapAsList(users, UserByParamOutDto.class);
    }

    @Override
    @Transactional
    public UserByIdOutDto userById(Integer userID) {
        User user = dao.loadUserById(userID);
        return mapper.mapUserToOutDto(user, UserByIdOutDto.class);
    }

    @Override
    @Transactional
    public void userUpdate(@Valid UserUpdateInDto userIn) {
        System.out.println("ID юзера до маппера: " + userIn);
        User user = mapper.mapUserInDtoToUser(userIn, User.class);
        System.out.println("ID юзера после маппера: " + user);
        dao.updateUser(user);
    }

    @Override
    @Transactional
    public void userSave(@Valid UserSaveInDto userIn) {
        User user = mapper.mapUserInDtoToUser(userIn, User.class);
        dao.saveUser(user);
    }
}
