package dao;


public interface CrudDAO<T> {
    void insert(T t);

    void update(T t);

    void deleteById(int id);

    T findById(int id);
}
