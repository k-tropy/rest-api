package ru.bolgov.bell.guide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bolgov.bell.guide.entity.DocType;
import ru.bolgov.bell.guide.repository.DocTypeRepository;

/**
 * {@inheritDoc}
 */
@Service
public class DocTypeServiceImpl implements DocTypeService{
    private final DocTypeRepository repository;

    @Autowired
    public DocTypeServiceImpl(DocTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<DocType> all() {
        return repository.findAll();
    }
}
