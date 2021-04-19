package ru.bolgov.bell.office.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bolgov.bell.office.dto.OfficeByParamInDto;
import ru.bolgov.bell.office.dto.OfficeByParamOutDto;
import ru.bolgov.bell.office.dto.OfficeInfoDto;
import ru.bolgov.bell.office.dto.OfficeNewDto;
import ru.bolgov.bell.office.service.OfficeService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Контроллер для работы с офисами
 */
@RestController
@RequestMapping(value ="/office", produces = APPLICATION_JSON_VALUE)
public class OfficeController {
    private final OfficeService service;

    @Autowired
    public OfficeController(OfficeService service) {
        this.service = service;
    }

    @PostMapping("/list")
    public List<OfficeByParamOutDto> byParam (@RequestBody OfficeByParamInDto parameters){
        return service.officesFilter(parameters);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public OfficeInfoDto byId(@PathVariable Integer id){
        return service.officeById(id);
    }

    @PostMapping("/update")
    public void update(@RequestBody OfficeInfoDto parameters){
        service.updateOffice(parameters);
    }

    @PostMapping("/save")
    public void save(@RequestBody OfficeNewDto parametrs){
        service.saveOffice(parametrs);
    }

}
