package ru.bolgov.bell.user.dao;

import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bolgov.bell.doc.entity.Doc;
import ru.bolgov.bell.guide.entity.Citizenship;
import ru.bolgov.bell.guide.entity.DocType;
import ru.bolgov.bell.office.entity.Office;
import ru.bolgov.bell.user.entity.User;
import ru.bolgov.bell.utils.exception.EntityNotFoundException;
import ru.bolgov.bell.utils.exception.NullArgumentException;

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
import java.util.Optional;

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
    public List<User> loadUsersByParam(User userIn, Integer officeId) {
        if (userIn == null || officeId == null) {
            throw new NullArgumentException();
        }
        CriteriaQuery<User> criteria = buildCriteria(userIn, officeId);
        TypedQuery<User> query = em.createQuery(criteria);
        List<User> users = query.getResultList();
        if (users.isEmpty()) {
            throw new EntityNotFoundException("Пользователей по заданным параметрам не найдено");
        }
        return users;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User loadUserById(Integer userId) {
        if (userId == null) {
            throw new NullArgumentException();
        }
        Optional<User> result = Optional.ofNullable(em.find(User.class, userId));
        return result.orElseThrow(() -> new EntityNotFoundException("Пользователь не найден id=" + userId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateUser(User userNew, Integer id, Integer officeId) {
        if (userNew == null || id == null) {
            throw new NullArgumentException();
        }
        User userOld = loadUserById(id);
        changeFieldValues(userNew, userOld, officeId);
        em.persist(userOld);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveUser(User user, Integer officeId) {
        if (user == null || officeId == null) {
            throw new NullArgumentException();
        }

        Doc doc = null;
        if (user.getDoc() != null) {
            doc = new Doc();
            doc.setNumber(user.getDoc().getNumber());
            doc.setDate(user.getDoc().getDate());
            if (user.getDoc().getDocType() != null) {
                Integer docCode = user.getDoc().getDocType().getCode();
                Optional<DocType> docTypeOptional = Optional.ofNullable(em.find(DocType.class, docCode));
                doc.setDocType(docTypeOptional.orElseThrow(() ->
                        new EntityNotFoundException("Такой тип документа не найден. Код документа:" + docCode)));
            }
            em.persist(doc);
        }

        String queryStringUser = "INSERT INTO User "
                + "(version, office_id, first_name, second_name, middle_name, position, phone, is_identified, citizenship_code, doc_id) "
                + "VALUES (0,:office_id, :first_name, :second_name, :middle_name, :position, :phone, :is_identified, :citizenship_code, :doc_id) ";
        Query query = em.createNativeQuery(queryStringUser);
        query.setParameter("office_id", officeId);
        query.setParameter("first_name", user.getFirstName());
        query.setParameter("second_name", user.getSecondName());
        query.setParameter("middle_name", user.getMiddleName());
        query.setParameter("position", user.getPosition());
        query.setParameter("phone", user.getPhone());
        query.setParameter("is_identified", user.getIsIdentified());
        query.setParameter("citizenship_code", user.getCitizenship() == null ? null : user.getCitizenship().getCode());
        query.setParameter("doc_id", doc == null ? null : doc.getId());
        query.executeUpdate();

    }

    private CriteriaQuery<User> buildCriteria(User userIn, Integer officeId) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> userRoot = criteria.from(User.class);
        Predicate mainPredicate = builder.equal(userRoot.get("office").get("id"), officeId);
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

    private void changeFieldValues(User userNew, User userOld, Integer officeId) {
        userOld.setFirstName(userNew.getFirstName());
        userOld.setPosition(userNew.getPosition());
        Optional.ofNullable(userNew.getSecondName()).ifPresent(secondNameNew -> userOld.setSecondName(secondNameNew));
        Optional.ofNullable(userNew.getMiddleName()).ifPresent(middleNameNew -> userOld.setMiddleName(middleNameNew));
        Optional.ofNullable(userNew.getPhone()).ifPresent(phoneNew -> userOld.setPhone(phoneNew));
        Optional.ofNullable(userNew.getIsIdentified()).ifPresent(isIdentifiedNew -> userOld.setIsIdentified(isIdentifiedNew));
        if (userNew.getDoc() != null) {
            Optional.ofNullable(userNew.getDoc().getNumber()).ifPresent(docNumber -> userOld.getDoc().setNumber(docNumber));
            Optional.ofNullable(userNew.getDoc().getDate()).ifPresent(docDate -> userOld.getDoc().setDate(docDate));
        }
        if (officeId != null) {
            Optional<Office> officeNewOptional = Optional.ofNullable(em.find(Office.class, officeId));
            userOld.setOffice(officeNewOptional.orElseThrow(() -> new EntityNotFoundException("Офис с таким id не найден. id=" + officeId)));
        }
        if (userNew.getCitizenship() != null) {
            Integer code = userNew.getCitizenship().getCode();
            Optional<Citizenship> citizenshipNew = Optional.ofNullable(em.find(Citizenship.class, code));
            userOld.setCitizenship(citizenshipNew.orElseThrow(() -> new EntityNotFoundException("Гражданство с таким кодом не найдено code=" + code)));
        }


    }
}

