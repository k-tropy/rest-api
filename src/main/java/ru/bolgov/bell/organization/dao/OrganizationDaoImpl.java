package ru.bolgov.bell.organization.dao;

import ru.bolgov.bell.organization.entity.Organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao {

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

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> loadOrganizationsByParam(Organization organization) {
        CriteriaQuery<Organization> criteria = buildCriteria(organization);
        TypedQuery<Organization> query = em.createQuery(criteria);
        return query.getResultList();
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
    public void updateOrganization(Organization organizationNew) {
        Organization organizationOld = em.find(Organization.class, organizationNew.getId());
        сhangeFieldValues(organizationNew, organizationOld);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOrganization(Organization organization) {
        em.persist(organization);
    }

    private CriteriaQuery<Organization> buildCriteria(Organization organization) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteria = builder.createQuery(Organization.class);
        Root<Organization> organizationRoot = criteria.from(Organization.class);

        Predicate mainPredicate = builder.equal(organizationRoot.get("name"), organization.getName());
        if (organization.getInn() != null) {
            Predicate innPredicate = builder.equal(organizationRoot.get("inn"), organization.getInn());
            mainPredicate = builder.and(mainPredicate, innPredicate);
        }
        if (organization.getIsActive() != null) {
            Predicate isActivePredicate = builder.equal(organizationRoot.get("isActive"), organization.getIsActive());
            mainPredicate = builder.and(mainPredicate, isActivePredicate);
        }

        return criteria.where(mainPredicate);
    }

    private void сhangeFieldValues(Organization organizationNew, Organization organizationOld) {
        if (organizationNew != null && organizationOld != null) {
            organizationOld.setName(organizationNew.getName());
            organizationOld.setFullName(organizationNew.getFullName());
            organizationOld.setInn(organizationNew.getInn());
            organizationOld.setKpp(organizationNew.getKpp());
            organizationOld.setAddress(organizationNew.getAddress());

            String phone = organizationNew.getPhone();
            if (phone != null) {
                organizationOld.setPhone(phone);
            }
            Boolean isActive = organizationNew.getIsActive();
            if (isActive != null) {
                organizationOld.setIsActive(isActive);
            }

        }
    }
}
