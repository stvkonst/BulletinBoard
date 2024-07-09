package service;

import domain.Ad;
import java.time.LocalDate;
import java.util.List;

public interface AdService extends CrudService<Ad> {
    void deleteAllByRubricId(int id);

    void deleteAllByAuthorId(int id);

    List<Ad> showByRubricIds(List<Integer> ids);

    void deleteByIsActiveIsFalse();

    List<Ad> findAllByAuthorId(int id);

    List<Ad> findAllByNameContains(String name);

    List<Ad> findAllByPublicationDate(LocalDate date);

    List<Ad> findAllPage(int page, int size);
}
