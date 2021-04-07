package ru.bolgov.bell.doc.entity;

import ru.bolgov.bell.guide.entity.DocType;
import ru.bolgov.bell.user.entity.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Doc")
public class Doc {

    /**
     * Идентификатор
     */
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * Номер документа удостоверяющего личность
     */
    @Column(name = "number", length = 50, nullable = false)
    private String number;

    /**
     * Дата выдачи документа
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false)
    private Date date;

    /**
     * Тип документа удостоверяющего личность
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code", nullable = false)
    private DocType docType;

    /**
     * Пользователь, которому принадлежит документ
     */
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    /**
     * Пустой конструктор для Hibernate
     */
    public Doc() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DocType getDocType() {
        return docType;
    }

    public void setDocType(DocType docType) {
        this.docType = docType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
