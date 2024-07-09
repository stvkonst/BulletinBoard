package service.impl;


import dao.AdDAO;
import domain.Ad;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import service.AdService;

import java.time.LocalDate;
import java.util.List;

@Repository
@AllArgsConstructor
public class AdServiceImpl implements AdService {
    AdDAO adDAO;

    @Override
    public void insert(Ad ad) {
        adDAO.insert(ad);
    }

    @Override
    public void update(Ad ad) {
        adDAO.update(ad);
    }

    @Override
    public void deleteById(int id) {
        adDAO.deleteById(id);
    }

    @Override
    public Ad findById(int id) {
        return adDAO.findById(id);
    }

    @Override
    public void deleteAllByRubricId(int id) {
        adDAO.deleteAllByRubricId(id);
    }

    @Override
    public void deleteAllByAuthorId(int id) {
        adDAO.deleteAllByAuthorId(id);
    }

    @Override
    public List<Ad> showByRubricIds(List<Integer> ids) {
        return adDAO.showByRubricIds(ids);
    }

    //@Scheduled(cron = "0/15 * * * * *")
    @Override
    public void deleteByIsActiveIsFalse() {
        adDAO.deleteByIsActiveIsFalse();
        System.out.println("Find to delete Ad");
    }

    @Override
    public List<Ad> findAllByAuthorId(int id) {
        return adDAO.findAllByAuthorId(id);
    }

    @Override
    public List<Ad> findAllByNameContains(String name) {
        return adDAO.findAllByNameContains(name);
    }

    @Override
    public List<Ad> findAllByPublicationDate(LocalDate date) {
        return adDAO.findAllByPublicationDate(date);
    }

    @Override
    public List<Ad> findAllPage(int page, int size) {
        return adDAO.findAllPage(page, size);
    }


}
