package dao.impl;

import dao.AdDAO;
import dao.SubscriptionDAO;
import domain.Ad;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import repository.AdRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
@AllArgsConstructor
public class AdDAOImpl implements AdDAO {
    AdRepository adRepository;

    SubscriptionDAO subscriptionDAO;

    @Override
    public void insert(Ad ad) {//id = 0
        adRepository.save(ad);
        List<String> emails = subscriptionDAO.getEmails(ad);
        //code that will send emails
    }

    @Override
    public void update(Ad ad) {//id != 0
        adRepository.save(ad);
    }

    @Override
    public void deleteById(int id) {
        adRepository.deleteById(id);
    }


    @Override
    public Ad findById(int id) {
        return adRepository.findById(id);
    }

    @Override
    public void deleteAllByAuthorId(int id) {
        adRepository.deleteAllByAuthorId(id);
    }

    @Override
    public void deleteAllByRubricId(int id) {
        adRepository.deleteAllByRubricId(id);
    }

    @Override
    public List<Ad> showByRubricIds(List<Integer> ids) {
        return adRepository.findAllByRubricIdIn(ids);
    }

    @Override
    public void deleteByIsActiveIsFalse() {
        adRepository.deleteByActiveIsFalse();
    }

    @Override
    public List<Ad> findAllByAuthorId(int id) {
        return adRepository.findAllByAuthorId(id);
    }

    @Override
    public List<Ad> findAllByNameContains(String name) {
        return adRepository.findAllByNameContains(name);
    }

    @Override
    public List<Ad> findAllByPublicationDate(LocalDate date) {
        return adRepository.findAllByPublicationDate(date);
    }

    @Override
    public List<Ad> findAllPage(int page, int size) {
        PageRequest request = PageRequest.of(page, size);

        Page<Ad> page1 = adRepository.findAll(request);

        return page1.getContent();
    }
}
