package ru.bolgov.bell.guide.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Тип документа удостоверяющего личность
 */
@Entity
@Table(name = "Doc_type")
public class DocType {

    /**
     * Код документа
     */
    @Id
    @Column(name = "code")
    private Integer code;

    /**
     * Название документа
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
