package service;

import domain.Ad;
import domain.Subscription;

import java.util.List;

public interface SubscriptionService extends CrudService<Subscription> {
    List<String> getEmails(Ad ad);
}
