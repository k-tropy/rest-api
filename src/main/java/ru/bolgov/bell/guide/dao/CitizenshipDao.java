package ru.bolgov.bell.guide.dao;

import ru.bolgov.bell.guide.entity.Citizenship;

import java.util.List;

/**
 * DAO интерфейс для работы со справочником
 * гражданств{@link Citizenship}
 */
public interface CitizenshipDao {

    /**
     * Получить список всех стран
     *
     * @return List<DocType> список странств
     */
    List<Citizenship> loadAllCitizenships();

    /**
     * Получить тип документа по коду
     *
     * @param citizenshipCode Код документа
     * @return тип документа
     */
    Citizenship loadDocTypeByCode(Integer citizenshipCode);
}
