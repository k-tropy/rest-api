package ru.bolgov.bell.guide.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bolgov.bell.guide.entity.DocType;

public interface DocTypeRepository extends CrudRepository<DocType, Integer> {
}
