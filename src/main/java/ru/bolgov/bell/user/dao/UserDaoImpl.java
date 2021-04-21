package ru.bolgov.bell.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bolgov.bell.doc.entity.Doc;
import ru.bolgov.bell.guide.entity.Citizenship;
import ru.bolgov.bell.guide.entity.DocType;
import ru.bolgov.bell.office.entity.Office;
import ru.bolgov.bell.user.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager em;

    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> loadAllUsers() {
        String queryString = "SELECT u FROM User u";
        TypedQuery<User> query = em.createQuery(queryString, User.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> loadUsersByParam(User userIn) {
        System.out.println(userIn.toString());
        CriteriaQuery<User> criteria = buildCriteria(userIn);
        TypedQuery<User> query = em.createQuery(criteria);
        return query.getResultList();
    }

    //Делает три select отдельно. Стоит переписать на скачивание всей информации ч/з один select

    /**
     * {@inheritDoc}
     */
    @Override
    public User loadUserById(Integer userId) {
        return em.find(User.class, userId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateUser(User userNew) {
        System.out.println("Id нового юзера: " + userNew.getId());
        User userOld = loadUserById(userNew.getId());
        changeFieldValues(userNew, userOld);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveUser(User user) {
        Doc doc = null;
//Не смог реализовать корректно только на нативном SQL. Не нашел способ из H2 получить id сохранённой записи
        if (user.getDoc() != null) {
            doc = new Doc();
            doc.setNumber(user.getDoc().getNumber());
            doc.setDate(user.getDoc().getDate());
            if (user.getDoc().getDocType() != null) {
                DocType docType = em.find(DocType.class, user.getDoc().getDocType().getCode());
                doc.setDocType(docType);
            }
            em.persist(doc);
        }

        String queryStringUser = "INSERT INTO User "
                + "(version, office_id, first_name, second_name, middle_name, position, phone, is_identified, citizenship_code, doc_id) "
                + "VALUES (0,:office_id, :first_name, :second_name, :middle_name, :position, :phone, :is_identified, :citizenship_code, :doc_id) ";
        Query query = em.createNativeQuery(queryStringUser);
        query.setParameter("office_id", user.getOffice().getId());
        query.setParameter("first_name", user.getFirstName());
        query.setParameter("second_name", user.getSecondName());
        query.setParameter("middle_name", user.getMiddleName());
        query.setParameter("position", user.getPosition());
        query.setParameter("phone", user.getPhone());
        query.setParameter("is_identified", user.getIsIdentified());
        query.setParameter("citizenship_code", user.getCitizenship() == null ? null : user.getCitizenship().getCode());
        query.setParameter("doc_id", doc == null ? null : doc.getId());
    }

    private CriteriaQuery<User> buildCriteria(User userIn) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> userRoot = criteria.from(User.class);

        Predicate mainPredicate = builder.equal(userRoot.get("office").get("id"), userIn.getOffice().getId());
        if (userIn.getFirstName() != null) {
            Predicate predicate = builder.equal(userRoot.get("firstName"), userIn.getFirstName());
            mainPredicate = builder.and(mainPredicate, predicate);
        }
        if (userIn.getSecondName() != null) {
            Predicate predicate = builder.equal(userRoot.get("secondName"), userIn.getSecondName());
            mainPredicate = builder.and(mainPredicate, predicate);
        }
        if (userIn.getMiddleName() != null) {
            Predicate predicate = builder.equal(userRoot.get("middleName"), userIn.getMiddleName());
            mainPredicate = builder.and(mainPredicate, predicate);
        }
        if (userIn.getPosition() != null) {
            Predicate predicate = builder.equal(userRoot.get("position"), userIn.getPosition());
            mainPredicate = builder.and(mainPredicate, predicate);
        }
        if (userIn.getDoc() != null
                && userIn.getDoc().getDocType() != null
                && userIn.getDoc().getDocType().getCode() != null) {
            Predicate predicate = builder.equal(
                    userRoot.get("doc").get("docType").get("code"),
                    userIn.getDoc().getDocType().getCode());
            mainPredicate = builder.and(mainPredicate, predicate);
        }

        if (userIn.getCitizenship() != null && userIn.getCitizenship().getCode() != null) {
            Predicate predicate = builder.equal(
                    userRoot.get("citizenship").get("code"),
                    userIn.getCitizenship().getCode());
            mainPredicate = builder.and(mainPredicate, predicate);
        }

        return criteria.where(mainPredicate);
    }


    private void changeFieldValues(User userNew, User userOld) {
        if (userNew != null && userOld != null) {
            userOld.setFirstName(userNew.getFirstName());
            userOld.setPosition(userNew.getPosition());

            String secondNameNew = userNew.getSecondName();
            if (secondNameNew != null) {
                userOld.setSecondName(secondNameNew);
            }

            String middleNameNew = userNew.getMiddleName();
            if (middleNameNew != null) {
                userOld.setMiddleName(middleNameNew);
            }

            String phoneNew = userNew.getPhone();
            if (phoneNew != null) {
                userOld.setPhone(phoneNew);
            }

            Boolean isIdentifiedNew = userNew.getIsIdentified();
            if (isIdentifiedNew != null) {
                userOld.setIsIdentified(isIdentifiedNew);
            }

            Office officeNew = userNew.getOffice();
            if (officeNew != null) {
                officeNew = em.find(Office.class, officeNew.getId());
                userOld.setOffice(officeNew);
            }

            Citizenship citizenshipNew = userNew.getCitizenship();
            if (citizenshipNew != null) {
                citizenshipNew = em.find(Citizenship.class, citizenshipNew.getCode());
                userOld.setCitizenship(citizenshipNew);
            }

            if (userNew.getDoc() != null) {
                String docNumber = userNew.getDoc().getNumber();
                if (docNumber != null) {
                    userOld.getDoc().setNumber(docNumber);
                }

                Date docDate = userNew.getDoc().getDate();
                if (docDate != null) {
                    userOld.getDoc().setDate(docDate);
                }
            }
        }
    }
}

