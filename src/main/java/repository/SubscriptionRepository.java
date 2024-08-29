package repository;

import domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    @Query(value = "SELECT s.author.email.name FROM Subscription s " +
            "WHERE s.rubric.name = :rubricName AND :adName LIKE CONCAT('%', s.name, '%')" +
            "AND :adPrice >= s.priceFrom AND :adPrice <= s.priceTo")
    List<String> getEmails(@Param(value = "rubricName") String rubricName,
                           @Param(value = "adName") String adName,
                           @Param(value = "adPrice") BigDecimal adPrice);

    void deleteByAuthorId(int id);

    Subscription findById(int id);
}
