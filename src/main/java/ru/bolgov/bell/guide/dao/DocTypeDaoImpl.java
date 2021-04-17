package ru.bolgov.bell.guide.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bolgov.bell.doc.entity.Doc;
import ru.bolgov.bell.guide.entity.DocType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class DocTypeDaoImpl implements DocTypeDao{

    private final EntityManager em;

    @Autowired
    public DocTypeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DocType> loadAllDocTypes() {
        String queryString = "SELECT dt FROM DocType dt";
        TypedQuery<DocType> query = em.createQuery(queryString, DocType.class);
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DocType loadDocTypeByCode(Integer docTypeCode) {
        return em.find(DocType.class, docTypeCode);
    }
}
