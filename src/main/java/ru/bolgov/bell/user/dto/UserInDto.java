package ru.bolgov.bell.user.dto;

import java.util.Date;

/**
 * Интерфейс объединяет методы необходимые для DTO принимающих параметры поиска
 */
public interface UserInDto {

    /**
     * Возвращает Id офиса, если передан
     *
     * @return
     */
    Integer getOfficeId();

    /**
     * Возвращает Код документа, если передан
     *
     * @return
     */
    Integer getDocCode();

    /**
     * Возвращает название документа, если передано
     *
     * @return
     */
    String getDocName();

    /**
     * Возвращает номер документа, если передан
     *
     * @return
     */
    String getDocNumber();

    /**
     * Возвращает дату документа, если передана
     *
     * @return
     */
    Date getDocDate();

    /**
     * Возвращает код гражданства, если передано
     *
     * @return
     */
    Integer getCitizenshipCode();
}
