package dao.impl;

import dao.SubscriptionDAO;
import domain.Ad;
import domain.Subscription;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import repository.SubscriptionRepository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
@AllArgsConstructor
public class SubscriptionDAOImpl implements SubscriptionDAO {
    SubscriptionRepository subscrRep;

    @Override
    public void insert(Subscription subscription) {
        subscrRep.save(subscription);
    }

    @Override
    public void update(Subscription subscription) {
        subscrRep.save(subscription);
    }

    @Override
    public void deleteById(int id) {
        subscrRep.deleteById(id);
    }

    @Override
    public Subscription findById(int id) {
        return subscrRep.findById(id).get();
    }

    @Override
    public List<String> getEmails(Ad ad) {
        String rubricName = ad.getRubric().getName();
        String adName = ad.getName();
        BigDecimal adPrice = ad.getPrice();
        return subscrRep.getEmails(rubricName, adName, adPrice);
    }
}
