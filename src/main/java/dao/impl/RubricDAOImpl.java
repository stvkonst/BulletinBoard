package dao.impl;

import dao.AdDAO;
import dao.CrudDAO;
import domain.Rubric;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RubricDAOImpl implements CrudDAO<Rubric> {
    @PersistenceContext
    EntityManager em;

    @Autowired
    AdDAO adDAO;

    @Override
    public void insert(Rubric rubric) {
        em.persist(rubric);
    }

    @Override
    public void update(Rubric rubric) {
        em.merge(rubric);
    }

    @Override
    public void deleteById(int id) {
        adDAO.deleteAllByRubricId(id);
        Rubric rubric = em.find(Rubric.class, id);
        em.remove(rubric);
    }

    @Override
    public Rubric findById(int id) {
        return em.find(Rubric.class, id);
    }
}
