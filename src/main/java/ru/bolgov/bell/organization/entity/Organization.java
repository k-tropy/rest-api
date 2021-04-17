package ru.bolgov.bell.organization.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Организация
 */
@Entity
@Table(name = "Organization")
public class Organization {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * Служебное поле Hibernate
     */
    @Version
    @Column(name = "version")
    private Integer version;

    /**
     * Сокращённое название организации
     */
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    /**
     * Полное название организации
     */
    @Column(name = "full_name", length = 255, nullable = false)
    private String fullName;

    /**
     * ИНН
     */
    @Column(name = "inn", length = 12, nullable = false, unique = true)
    private String inn;

    /**
     * КПП
     */
    @Column(name = "kpp", length = 9, nullable = false)
    private String kpp;

    /**
     * Юридический адрес организации
     */
    @Column(name = "address", length = 255, nullable = false)
    private String address;

    /**
     * Телефон
     */
    @Column(name = "phone", length = 16)
    private String phone;

    /**
     * Статус организации(метка активности)
     */
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
