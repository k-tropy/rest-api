package ru.bolgov.bell.guide.service;

import ru.bolgov.bell.guide.entity.DocType;

/**
 * Сервис для работы со справочником документов удостоверяющих личность
 */
public interface DocTypeService {

    /**
     * Загрузить полный список документов удостоверяющих личность
     *
     * @return
     */
    Iterable<DocType> all();
}
