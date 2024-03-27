package service;


public interface CrudService<T> {
    void insert(T t);

    void update(T t);

    void deleteById(int id);

    T findById(int id);
}
