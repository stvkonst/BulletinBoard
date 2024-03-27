package dao.impl;

import dao.AdDAO;
import dao.CrudDAO;
import domain.Author;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Repository
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorDAOImpl implements CrudDAO<Author> {

    @Autowired
    AdDAO adDAO;

    @PersistenceContext
    EntityManager em;

    @Override
    public void insert(Author author) {
        em.persist(author);
    }

    @Override
    public void update(Author author) {
        em.merge(author);
    }

    @Override
    public void deleteById(int id) {
        adDAO.deleteAllByAuthorId(id);

        deleteAuthorFields(id);

        deleteEntity("DELETE FROM Author a WHERE a.id =: a_id", id);
    }

    private void deleteAuthorFields(int id) {
        deletePhoneByAuthorId(id);
        deleteAddressByAuthorId(id);
        deleteEmailByAuthorId(id);
    }

    @Override
    public Author findById(int id) {
        TypedQuery<Author> query =
                em.createQuery("FROM Author a JOIN FETCH a.phones WHERE a.id = :a_id", Author.class);
        query.setParameter("a_id", id);

        return query.getSingleResult();
    }

    private void deletePhoneByAuthorId(int id) {
        deleteEntity("DELETE FROM Phone p WHERE p.author.id =: a_id", id);
    }

    private void deleteAddressByAuthorId(int id) {
        deleteEntity("DELETE FROM Address a WHERE a.author.id =: a_id", id);
    }

    private void deleteEmailByAuthorId(int id) {
        deleteEntity("DELETE FROM Email e WHERE e.author.id =: a_id", id);
    }

    private void deleteEntity(String qlString, int id) {
        Query query = em.createQuery(qlString);
        query.setParameter("a_id", id);
        query.executeUpdate();
    }
}
