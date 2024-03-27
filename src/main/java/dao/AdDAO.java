package dao;

import domain.Ad;

import java.util.List;

public interface AdDAO extends CrudDAO<Ad> {
    void deleteAllByRubricId(int id);

    void deleteAllByAuthorId(int id);

    List<Ad> showByRubricIds(List<Integer> ids);

}
