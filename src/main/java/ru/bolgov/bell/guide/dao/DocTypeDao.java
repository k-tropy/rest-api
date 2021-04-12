package ru.bolgov.bell.guide.dao;

import ru.bolgov.bell.guide.entity.DocType;
import ru.bolgov.bell.organization.entity.Organization;

import java.util.List;

/**
 * DAO интерфейс для работы со справочником документов
 * удостоверяющих личность{@link DocType}
 */
public interface DocTypeDao {

    /**
     * Получить список всех типов документов
     *
     * @return List<DocType> список типов документов
     */
    List<DocType> loadAllDocTypes();

    /**
     * Получить тип документа по коду
     *
     * @param docTypeCode Код документа
     * @return тип документа
     */
    DocType loadDocTypeByCode(Integer docTypeCode);
}
