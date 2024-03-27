package service;

import domain.Ad;
import java.util.List;

public interface AdService extends CrudService<Ad> {
    void deleteAllByRubricId(int id);

    void deleteAllByAuthorId(int id);

    List<Ad> showByRubricIds(List<Integer> ids);

}
