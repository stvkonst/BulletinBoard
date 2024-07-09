package repository;

import domain.Author;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Author findById(int id);


//    @Query(value = "FROM Author a JOIN FETCH a.phones WHERE a.id = :a_id")
//    Author findById(@Param(value = "a_id") int id);




}
