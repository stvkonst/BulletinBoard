package domain;


import config.ConfigApp;
import dao.impl.AdDAOImpl;
import dao.impl.AuthorDAOImpl;
import dao.impl.RubricDAOImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;


public class TestService {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(ConfigApp.class);

    AuthorDAOImpl authorDAO = context.getBean(AuthorDAOImpl.class);
    RubricDAOImpl rubricDAO = context.getBean(RubricDAOImpl.class);
    AdDAOImpl adDAO = context.getBean(AdDAOImpl.class);


//    List<Integer> ids = List.of(1, 2);
//    adDAO.showByRubricIds(ids).forEach(System.out::println);

//        authorService.insertAuthor("0501112233", "1 maja, 33", "mm@gmail.com", "John Born");
//        rubricService.insertRubric("Auto");
//        adService.insertAd(1, 1, "BMW i316 for sale", LocalDate.now(), 3000, "Very good BMW i316 for sale, 2003, first owner, very good condition");

//        authorService.updateAuthorPhone(1, 0, "0995556677");
//        authorService.updateAuthorAddress(1, "Alpejska, 16, Krakow");
//        authorService.updateAuthorEmail(1, "www@tt.com");
//        authorService.updateAuthorName(1,"David Bowie");

//        rubricService.updateRubricName(1, "Automobiles");
//
//        adService.updateAdName(1, "BMW i325 for sale");
//        adService.updateAdText(1,"Very good BMW i325 for sale, 2003, first owner, very good condition");
//        adService.updateAdPrice(1, 4000);

//        Author tepmAuthor = authorService.find(1);
//        Email tempEmail = tepmAuthor.getEmail();
//        tempEmail.setName("eee@mm.com");
//        authorService.update(tepmAuthor);



        //authorDAO.deleteById(1);



    }
}

