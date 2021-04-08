package ru.bolgov.bell.organization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bolgov.bell.organization.service.OrgService;
import ru.bolgov.bell.organization.dto.OrgDtoOutShort;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class OrgController {
    private final OrgService orgService;

    @Autowired
    public OrgController(OrgService orgService) {
        this.orgService = orgService;
    }

    /*@PostMapping("/org")
    public void person(@RequestBody OrgDtoOutShort org) {
        personService.add(person);
    }*/

    @GetMapping("/org")
    public List<OrgDtoOutShort> orgs() {
        return orgService.organization();
    }
}
