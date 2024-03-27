package domain;


import service.impl.AdServiceImpl;
import service.impl.AuthorServiceImpl;
import service.impl.RubricServiceImpl;
import java.time.LocalDate;


public class TestService {
    public static void main(String[] args) {
        AuthorServiceImpl authorService = new AuthorServiceImpl();
        RubricServiceImpl rubricService = new RubricServiceImpl();
        AdServiceImpl adService = new AdServiceImpl();

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

