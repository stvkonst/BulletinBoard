package service.impl;


import dao.AdDAO;
import dao.impl.AdDAOImpl;
import domain.Ad;
import org.springframework.stereotype.Repository;
import service.AdService;
import java.util.List;

@Repository
public class AdServiceImpl implements AdService {
    AdDAO adDAO;

    public AdServiceImpl() {
        this.adDAO = new AdDAOImpl();
    }

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
}






//    AnnotationConfigApplicationContext context =
//            new AnnotationConfigApplicationContext(ConfigApp.class);
//
//    AuthorDAOImpl authorDAO = context.getBean(AuthorDAOImpl.class);
//    RubricDAOImpl rubricDAO = context.getBean(RubricDAOImpl.class);
//    AdDAOImpl adDAO = context.getBean(AdDAOImpl.class);
//
//    public void insertAd(int authorId, int rubricId, String name, LocalDate date, long price, String text) {
//        Author tempAuthor = authorDAO.findById(authorId);
//        Rubric tempRubric = rubricDAO.findById(rubricId);
//
//        Ad ad = Ad.builder()
//                .name(name)
//                .publicationDate(date)
//                .price(price)
//                .text(text)
//                .author(tempAuthor)
//                .rubric(tempRubric)
//                .build();
//
//        adDAO.insert(ad);
//    }
//
//    public void updateAdName(int adId, String newName) {
//        Ad updatedAd = adDAO.findById(adId);
//
//        updatedAd.setName(newName);
//
//        adDAO.update(updatedAd);
//    }
//
//    public void updateAdText(int adId, String newText) {
//        Ad updatedAd = adDAO.findById(adId);
//
//        updatedAd.setText(newText);
//
//        adDAO.update(updatedAd);
//    }
//
//    public void updateAdPrice(int adId, long price) {
//        Ad updatedAd = adDAO.findById(adId);
//
//        updatedAd.setPrice(price);
//
//        adDAO.update(updatedAd);
//    }
//
//    public void deleteAd(int adId) {
//        adDAO.deleteById(adId);
//    }
//
//    public void deleteAllByAuthorId(int authorId) {
//        adDAO.deleteAllByAuthorId(authorId);
//    }
//
//    public List<Ad> showByRubricIds(List<Integer> ids) {
//        return adDAO.showByRubricIds(ids);
//    }
//}
