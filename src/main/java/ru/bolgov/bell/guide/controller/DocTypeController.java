package ru.bolgov.bell.guide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bolgov.bell.guide.entity.DocType;
import ru.bolgov.bell.guide.service.DocTypeService;

/**
 * Контроллер для работы с разными типами документов удостоверяющих личность
 */
@RestController
@RequestMapping(value = "/api/docs")
public class DocTypeController {
    private final DocTypeService service;

    @Autowired
    public DocTypeController(DocTypeService service) {
        this.service = service;
    }

    /**
     * Получения перечня всех типов документов в справочнике
     *
     * @return
     */
    @PostMapping
    public Iterable<DocType> all(){
        return service.all();
    }
}
