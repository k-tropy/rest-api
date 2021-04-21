package ru.bolgov.bell.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bolgov.bell.user.dto.*;
import ru.bolgov.bell.user.service.UserService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Контроллер для работы с пользователями
 */
@RestController
@RequestMapping (value = "/api/user", produces = APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<UserByParamOutDto> all(){
       return service.allUsers();
    }

    @PostMapping("/list")
    public List<UserByParamOutDto> byParam(@RequestBody UserByParamInDto parameters){
        return service.usersFilter(parameters);
    }

    @GetMapping("/{id}")
    public UserByIdOutDto byId(@PathVariable Integer id){
        return service.userById(id);
    }

    @PostMapping("/update")
    public void update(@RequestBody UserUpdateInDto userIn){
        service.userUpdate(userIn);
    }

    @PostMapping("/save")
    public void save(@RequestBody UserSaveInDto userIn){
        service.userSave(userIn);
    }

}
