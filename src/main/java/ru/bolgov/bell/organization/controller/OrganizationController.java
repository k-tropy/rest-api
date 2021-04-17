package ru.bolgov.bell.organization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bolgov.bell.organization.dto.OrganizationByParamInDto;
import ru.bolgov.bell.organization.dto.OrganizationByParamOutDto;
import ru.bolgov.bell.organization.dto.OrganizationFullDto;
import ru.bolgov.bell.organization.dto.OrganizationWithoutIdDto;
import ru.bolgov.bell.organization.service.OrganizationService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/organization", produces = APPLICATION_JSON_VALUE)
public class OrganizationController {
    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService orgService) {
        this.organizationService = orgService;
    }

    /*@PostMapping("/org")
    public void person(@RequestBody OrgDtoOutShort org) {
        personService.add(person);
    }*/

    @GetMapping("/all")
    public List<OrganizationFullDto> all() {
        return organizationService.organizations();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public OrganizationFullDto byId(@PathVariable Integer id) {
        return organizationService.organizationById(id);
    }

    @PostMapping("/list")
    public List<OrganizationByParamOutDto> byParam(@RequestBody OrganizationByParamInDto parameters){
        return organizationService.organizationsFilter(parameters);
    }

    @PostMapping("/update")
    public void update(@RequestBody OrganizationFullDto parametrs){
        organizationService.updateOrganization(parametrs);
    }

    @PostMapping("/save")
    public void save(@RequestBody OrganizationWithoutIdDto parameters){
        organizationService.saveOrganization(parameters);
    }

}
