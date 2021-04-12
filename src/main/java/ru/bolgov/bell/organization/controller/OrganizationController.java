package ru.bolgov.bell.organization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bolgov.bell.organization.dto.OrganizationFullDto;
import ru.bolgov.bell.organization.service.OrganizationService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
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

    @GetMapping("/org")
    public List<OrganizationFullDto> orgs() {
        return organizationService.organization();
    }
}
