package ru.bolgov.bell.guide.service;

import ru.bolgov.bell.guide.entity.Citizenship;

/**
 * Сервис для работы с гражданством
 */
public interface CitizenshipService {

    /**
     * Получить список гражданств
     * @return
     */
    Iterable<Citizenship> all();
}
