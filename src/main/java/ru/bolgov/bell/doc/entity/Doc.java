package ru.bolgov.bell.doc.entity;

import ru.bolgov.bell.guide.entity.DocType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Doc")
public class Doc {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Integer getId() {
        return id;
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
