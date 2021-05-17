package ru.bolgov.bell.guide.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bolgov.bell.guide.entity.Citizenship;

public interface CitizenshipRepository extends CrudRepository<Citizenship, Integer> {

}
