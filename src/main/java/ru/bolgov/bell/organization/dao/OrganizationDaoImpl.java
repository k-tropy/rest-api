package ru.bolgov.bell.organization.dao;

import ru.bolgov.bell.organization.entity.Organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bolgov.bell.utils.exception.EntityNotFoundException;
import ru.bolgov.bell.utils.exception.NullArgumentException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

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
    public List<Organization> loadOrganizationsByParam(Organization organizationIn) {
        if (organizationIn == null) {
            throw new NullArgumentException();
        }

        CriteriaQuery<Organization> criteria = buildCriteria(organizationIn);
        TypedQuery<Organization> query = em.createQuery(criteria);
        List<Organization> result = query.getResultList();
        if (result.isEmpty()) {
            throw new EntityNotFoundException("Нет результатов по вашему запросу. Попробуйте изменить параметры");
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization loadOrganizationById(Integer organizationId) {
        if (organizationId == null) {
            throw new NullArgumentException();
        }
        Optional<Organization> result = Optional.ofNullable(em.find(Organization.class, organizationId));
        return result.orElseThrow(() -> new EntityNotFoundException("Нет результатов для id=" + organizationId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateOrganization(Organization organizationNew, Integer id) {
        if (organizationNew == null || id == null) {
            throw new NullArgumentException();
        }
        Optional<Organization> organizationOld = Optional.ofNullable(em.find(Organization.class, id));
        сhangeFieldValues(organizationNew,
                organizationOld.orElseThrow(() -> new EntityNotFoundException("Организация id=" + id + " не найдена; ")));
        organizationOld.ifPresent(v -> em.persist(v));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOrganization(Organization organization) {
        if (organization == null) {
            throw new NullArgumentException();
        }
        em.persist(organization);
    }

    private CriteriaQuery<Organization> buildCriteria(Organization organizationIn) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteria = builder.createQuery(Organization.class);
        Root<Organization> organizationRoot = criteria.from(Organization.class);

        Predicate mainPredicate = builder.equal(organizationRoot.get("name"), organizationIn.getName());
        if (organizationIn.getInn() != null) {
            Predicate innPredicate = builder.equal(organizationRoot.get("inn"), organizationIn.getInn());
            mainPredicate = builder.and(mainPredicate, innPredicate);
        }
        if (organizationIn.getIsActive() != null) {
            Predicate isActivePredicate = builder.equal(organizationRoot.get("isActive"), organizationIn.getIsActive());
            mainPredicate = builder.and(mainPredicate, isActivePredicate);
        }

        return criteria.where(mainPredicate);
    }

    private void сhangeFieldValues(Organization organizationNew, Organization organizationOld) {
        organizationOld.setName(organizationNew.getName());
        organizationOld.setFullName(organizationNew.getFullName());
        organizationOld.setInn(organizationNew.getInn());
        organizationOld.setKpp(organizationNew.getKpp());
        organizationOld.setAddress(organizationNew.getAddress());

        Optional.ofNullable(organizationNew.getPhone())
                .ifPresent(phoneNew -> organizationOld.setPhone(phoneNew));

        Optional.ofNullable(organizationNew.getIsActive())
                .ifPresent(isActiveNew -> organizationOld.setIsActive(isActiveNew));

    }
}
