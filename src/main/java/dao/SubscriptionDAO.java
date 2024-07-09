package dao;

import domain.Ad;
import domain.Subscription;

import java.util.List;

public interface SubscriptionDAO extends CrudDAO<Subscription>{
    List<String> getEmails(Ad ad);
}
