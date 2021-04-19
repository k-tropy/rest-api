package ru.bolgov.bell.organization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bolgov.bell.organization.dto.OrganizationByParamInDto;
import ru.bolgov.bell.organization.dto.OrganizationByParamOutDto;
import ru.bolgov.bell.organization.dto.OrganizationFullDto;
import ru.bolgov.bell.organization.dto.OrganizationWithoutIdDto;
import ru.bolgov.bell.organization.service.OrganizationService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Контроллер для работы с организациями
 */
@RestController
@RequestMapping(value = "/organization", produces = APPLICATION_JSON_VALUE)
public class OrganizationController {
    private final OrganizationService service;

    @Autowired
    public OrganizationController(OrganizationService orgService) {
        this.service = orgService;
    }

    /**
     * Получение списка всех организаций
     *
     * @return список организаций
     */
    @GetMapping("/all")
    public List<OrganizationFullDto> all() {
        return service.organizations();
    }

    /**
     * Получение организации по id
     *
     * @param id
     * @return организация
     */
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public OrganizationFullDto byId(@PathVariable Integer id) {
        return service.organizationById(id);
    }

    /**
     * Получение списка организаций по параметрам
     *
     * @param parameters
     * @return список организаций
     */
    @PostMapping("/list")
    public List<OrganizationByParamOutDto> byParam(@RequestBody OrganizationByParamInDto parameters){
        return service.organizationsFilter(parameters);
    }

    /**
     * Обновление информации о организации
     *
     * @param parametrs
     */
    @PostMapping("/update")
    public void update(@RequestBody OrganizationFullDto parametrs){
        service.updateOrganization(parametrs);
    }

    /**
     * Добавление новой организации
     *
     * @param parameters
     */
    @PostMapping("/save")
    public void save(@RequestBody OrganizationWithoutIdDto parameters){
        service.saveOrganization(parameters);
    }

}
