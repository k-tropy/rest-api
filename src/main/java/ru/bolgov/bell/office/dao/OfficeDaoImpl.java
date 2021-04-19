package ru.bolgov.bell.office.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bolgov.bell.office.entity.Office;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
public class OfficeDaoImpl implements OfficeDao {

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

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> loadOfficesByParam(Office officeIn) {
        CriteriaQuery<Office> criteria = buildCriteria(officeIn);
        TypedQuery<Office> query = em.createQuery(criteria);
        return query.getResultList();
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
    public void updateOffice(Office officeNew) {
        Office officeOld = em.find(Office.class, officeNew.getId());
        changeFieldValues(officeNew, officeOld);
    }

    //Можно сохранить через персист но тогда будет дополнительный запрос в БД для скачивания информации о организации. Или можно иначе сделать?
    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOffice(Office office) {
        String queryString = "INSERT INTO office(version, org_id, name, address, phone, is_active) VALUES (0,?,?,?,?,?)";
        Query query = em.createNativeQuery(queryString);
        query.setParameter(1, office.getOrganization().getId());
        query.setParameter(2, office.getName());
        query.setParameter(3, office.getAddress());
        query.setParameter(4, office.getPhone());
        query.setParameter(5, office.getIsActive());
        query.executeUpdate();
        //em.persist(office); - просто запросить организацию по ID и всё заработает!!!
    }

    private CriteriaQuery<Office> buildCriteria(Office officeIn) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteria = builder.createQuery(Office.class);
        Root<Office> officeRoot = criteria.from(Office.class);

        Predicate mainPredicate = builder.equal(officeRoot.get("organization").get("id"), officeIn.getOrganization().getId());
        if (officeIn.getName() != null) {
            Predicate namePredicate = builder.equal(officeRoot.get("name"), officeIn.getName());
            mainPredicate = builder.and(mainPredicate, namePredicate);
        }
        if (officeIn.getPhone() != null) {
            Predicate phonePredicate = builder.equal(officeRoot.get("phone"), officeIn.getPhone());
            mainPredicate = builder.and(mainPredicate, phonePredicate);
        }
        if (officeIn.getIsActive() != null) {
            Predicate isActivePredicate = builder.equal(officeRoot.get("isActive"), officeIn.getIsActive());
            mainPredicate = builder.and(mainPredicate, isActivePredicate);
        }
        return criteria.where(mainPredicate);
    }

    private void changeFieldValues(Office officeNew, Office officeOld) {
        if (officeNew != null && officeOld != null) {
            officeOld.setName(officeNew.getName());
            officeOld.setAddress(officeNew.getAddress());

            String phoneNew = officeNew.getPhone();
            if (phoneNew != null) {
                officeOld.setPhone(phoneNew);
            }
            Boolean isActiveNew = officeNew.getIsActive();
            if (isActiveNew != null) {
                officeOld.setIsActive(isActiveNew);
            }
        }
    }
}
