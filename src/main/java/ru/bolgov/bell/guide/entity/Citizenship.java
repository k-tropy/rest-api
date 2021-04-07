package ru.bolgov.bell.guide.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Гражданство
 */
@Entity
@Table(name = "Сitizenship")
public class Citizenship {

    /**
     * Код гражданства
     */
    @Id
    @Column(name = "code")
    private Integer code;

    /**
     * Название страны
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * Пустой конструктор для Hibernate
     */
    public Citizenship() {
    }

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
