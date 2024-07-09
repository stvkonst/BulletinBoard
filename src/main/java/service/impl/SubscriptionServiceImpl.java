package service.impl;

import dao.SubscriptionDAO;
import domain.Ad;
import domain.Subscription;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import service.SubscriptionService;
import java.util.List;

@Repository
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    SubscriptionDAO subscriptionDAO;

    @Override
    public List<String> getEmails(Ad ad) {
        return subscriptionDAO.getEmails(ad);
    }

    @Override
    public void insert(Subscription subscription) {
        subscriptionDAO.insert(subscription);
    }

    @Override
    public void update(Subscription subscription) {
        subscriptionDAO.update(subscription);
    }

    @Override
    public void deleteById(int id) {
        subscriptionDAO.deleteById(id);
    }

    @Override
    public Subscription findById(int id) {
        return subscriptionDAO.findById(id);
    }
}
