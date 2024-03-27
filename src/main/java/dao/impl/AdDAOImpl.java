package dao.impl;

import dao.AdDAO;
import domain.Ad;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class AdDAOImpl implements AdDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public void insert(Ad ad) {//id = 0
        em.persist(ad);
    }

    @Override
    public void update(Ad ad) {//id != 0
        em.merge(ad);
    }

    @Override
    public void deleteById(int id) {
        Query query = em.createQuery("DELETE FROM Ad a WHERE a.id =: a_id");
        query.setParameter("a_id", id);
        query.executeUpdate();
    }

    @Override
    public Ad findById(int id) {
        return em.find(Ad.class, id);
    }

    @Override
    public void deleteAllByAuthorId(int id) {
        Query query = em.createQuery("DELETE FROM Ad a WHERE a.author.id =: a_id");
        query.setParameter("a_id", id);

        query.executeUpdate();
    }

    @Override
    public void deleteAllByRubricId(int id) {
        Query query = em.createQuery("DELETE FROM Ad a WHERE a.rubric.id =: r_id");
        query.setParameter("r_id", id);

        query.executeUpdate();
    }

    @Override
    public List<Ad> showByRubricIds(List<Integer> ids) {
        TypedQuery<Ad> query =
                em.createQuery("FROM Ad a WHERE a.rubric.id IN :ids", Ad.class);
        query.setParameter("ids", ids);

        return query.getResultList();
    }
}
