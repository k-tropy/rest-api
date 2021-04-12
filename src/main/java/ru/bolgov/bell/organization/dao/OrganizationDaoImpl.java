package ru.bolgov.bell.organization.dao;

import ru.bolgov.bell.organization.entity.Organization;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    @PersistenceContext
    private final EntityManager em;

    @Autowired
    public OrganizationDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> loadAllOrganizations() {
        String queryString = "SELECT o FROM Organization o";
        TypedQuery<Organization> query = em.createQuery(queryString, Organization.class);
        return query.getResultList();
    }
// TODO: 10.04.2021 Написать метод через критерия 
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> loadOrganizationsByParam(Organization organization) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization loadOrganizationById(Integer organizationId) {
        return em.find(Organization.class, organizationId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateOrganization(Organization organization) {
        em.refresh(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOrganization(Organization organization) {
        em.persist(organization);
    }

    // TODO: 10.04.2021 Реализовать метод сборки запроса 
    private CriteriaQuery<Organization> buildCriteria(){
        return null;
    }
}
