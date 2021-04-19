package ru.bolgov.bell.guide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bolgov.bell.guide.entity.Citizenship;
import ru.bolgov.bell.guide.service.CitizenshipService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Контроллер для работы с гражданствами
 */
@RestController
@RequestMapping(value ="/api/countries", produces = APPLICATION_JSON_VALUE)
public class CitizenshipController {
    private final CitizenshipService service;

    @Autowired
    public CitizenshipController(CitizenshipService service) {
        this.service = service;
    }

    @PostMapping
    public Iterable<Citizenship> all(){
        return service.all();
    }
}
