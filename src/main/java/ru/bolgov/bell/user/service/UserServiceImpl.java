package ru.bolgov.bell.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bolgov.bell.utils.mapper.MapperFacade;
import ru.bolgov.bell.user.dao.UserDao;
import ru.bolgov.bell.user.dto.*;
import ru.bolgov.bell.user.entity.User;

import javax.crypto.spec.PSource;
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
        List<User> users = dao.loadUsersByParam(parameters,filterInDto.officeId);
        return mapper.mapAsList(users, UserByParamOutDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public UserByIdOutDto userById(Integer userID) {
        User user = dao.loadUserById(userID);
        return mapper.mapUserToOutDto(user, UserByIdOutDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void userUpdate(@Valid UserUpdateInDto userIn) {
        User user = mapper.mapUserInDtoToUser(userIn, User.class);
        Integer id = userIn.id;
        Integer officeId = userIn.officeId;
        dao.updateUser(user, id, officeId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void userSave(@Valid UserSaveInDto userIn) {
        User user = mapper.mapUserInDtoToUser(userIn, User.class);
        Integer officeId = userIn.officeId;
        dao.saveUser(user, officeId);
    }
}
