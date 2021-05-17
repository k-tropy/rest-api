package ru.bolgov.bell.guide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bolgov.bell.guide.entity.Citizenship;
import ru.bolgov.bell.guide.repository.CitizenshipRepository;

/**
 * {@inheritDoc}
 */
@Service
public class CitizenshipServiceImpl implements CitizenshipService{

    private final CitizenshipRepository repository;

    @Autowired
    public CitizenshipServiceImpl(CitizenshipRepository repository) {
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<Citizenship> all() {
        return repository.findAll();
    }
}
