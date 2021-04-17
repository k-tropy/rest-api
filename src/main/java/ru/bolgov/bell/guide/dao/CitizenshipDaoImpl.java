package ru.bolgov.bell.guide.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bolgov.bell.guide.entity.Citizenship;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class CitizenshipDaoImpl implements CitizenshipDao{

    private final EntityManager em;

    @Autowired
    public CitizenshipDaoImpl(EntityManager em){
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Citizenship> loadAllCitizenships() {
        String queryString = "SELECT c FROM Citizenship c";
        TypedQuery<Citizenship> query= em.createQuery(queryString, Citizenship.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Citizenship loadDocTypeByCode(Integer citizenshipCode) {
        return em.find(Citizenship.class, citizenshipCode);
    }
}
