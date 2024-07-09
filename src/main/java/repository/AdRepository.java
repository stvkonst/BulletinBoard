package repository;

import domain.Ad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Integer> {
    void deleteAllByAuthorId(int id);

    void deleteAllByRubricId(int id);

    @EntityGraph(attributePaths = {"author.phones", "rubric"})
    List<Ad> findAllByRubricIdIn(List<Integer> ids);

    @EntityGraph(attributePaths = {"author.phones", "rubric"})
    Ad findById(int id);

//    @EntityGraph(attributePaths = {"author.phones", "rubric"})
//    Page<Ad> findAll(PageRequest request);

    void deleteByActiveIsFalse();

    @EntityGraph(attributePaths = {"author.phones", "rubric"})
    List<Ad> findAllByAuthorId(int id);

    @EntityGraph(attributePaths = {"author.phones", "rubric"})
    List<Ad> findAllByNameContains(String name);

    @EntityGraph(attributePaths = {"author.phones", "rubric"})
    List<Ad> findAllByPublicationDate(LocalDate date);
}
