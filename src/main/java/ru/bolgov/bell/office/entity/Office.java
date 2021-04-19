package ru.bolgov.bell.office.entity;

import ru.bolgov.bell.organization.entity.Organization;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Офис
 */
@Entity
@Table(name = "Office")
public class Office {

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
     * Название офиса
     */
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    /**
     * Адрес офиса
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

    /**
     * Организация которой принадлежит офис
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id", nullable = false)
    private Organization organization;

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

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        return "Office{" +
                "id=" + id +
                ", version=" + version +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", isActive=" + isActive +
                ", organization=" + organization +
                '}';
    }
}
