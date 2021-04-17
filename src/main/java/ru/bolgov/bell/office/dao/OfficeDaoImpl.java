package ru.bolgov.bell.office.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bolgov.bell.office.entity.Office;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class OfficeDaoImpl implements OfficeDao{

    private final EntityManager em;

    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> loadAllOffices() {
        String queryString = "SELECT o FROM Office o";
        TypedQuery<Office> query = em.createQuery(queryString, Office.class);
        return query.getResultList();
    }
// TODO: 10.04.2021 Написать метод через Критерия!!!!!!!!!!!!
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> loadOfficesByParam(Office office) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office loadOfficeById(Integer officeId) {
        return em.find(Office.class, officeId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateOffice(Office office) {
        em.refresh(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOffice(Office office) {
        em.persist(office);
    }

    // TODO: 10.04.2021 Реализовать метод сборки запроса через критерия 
}
