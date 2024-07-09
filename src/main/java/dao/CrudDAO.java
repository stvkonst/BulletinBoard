package dao;


import javax.persistence.EntityManager;
import javax.persistence.Query;

public interface CrudDAO<T> {
    void insert(T t);

    default void insertClass(T t, EntityManager em) {
        em.persist(t);
    }

    void update(T t);

    void deleteById(int id);

    default void deleteByIdClass(int id, Class<T> tClass, EntityManager em) {
        Query query = em.createQuery("DELETE FROM " + tClass.getSimpleName() + " t WHERE t.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    default T findByIdClass(int id, Class<T> aClass, EntityManager em) {
        return em.find(aClass, id);
    }

    T findById(int id);
}
