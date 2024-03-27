package service.impl;

import dao.CrudDAO;
import dao.impl.AuthorDAOImpl;
import domain.Author;
import org.springframework.stereotype.Repository;
import service.CrudService;

@Repository
public class AuthorServiceImpl implements CrudService<Author> {
    CrudDAO<Author> authorDAO;

    public AuthorServiceImpl() {
        this.authorDAO = new AuthorDAOImpl();
    }

    @Override
    public void insert(Author author) {
        authorDAO.insert(author);
    }

    @Override
    public void update(Author author) {
        authorDAO.update(author);
    }

    @Override
    public void deleteById(int id) {
        authorDAO.deleteById(id);
    }

    @Override
    public Author findById(int id) {
        return authorDAO.findById(id);
    }
}


//    public void updateAuthorPhone(int authorId, int phoneIndex, String newPhone) {
//        Author updatedAuthor = authorDAO.findById(authorId);
//
//        List<Phone> updatedAuthorPhones = updatedAuthor.getPhones();
//
//        Phone addedPhone = updatedAuthorPhones.get(phoneIndex);
//
//        addedPhone.setName(newPhone);
//
//        updatedAuthorPhones.set(phoneIndex, addedPhone);
//
//        authorDAO.update(updatedAuthor);
//    }
//
//    public void updateAuthorAddress(int authorId, String newAddress) {
//        Author updatedAuthor = authorDAO.findById(authorId);
//
//        Address updatedAuthorAddress = updatedAuthor.getAddress();
//
//        updatedAuthorAddress.setName(newAddress);
//
//        authorDAO.update(updatedAuthor);
//    }
//
//    public void updateAuthorEmail(int authorId, String newEmail) {
//        Author updatedAuthor = authorDAO.findById(authorId);
//
//        Email updatedAuthorEmail = updatedAuthor.getEmail();
//
//        updatedAuthorEmail.setName(newEmail);
//
//        authorDAO.update(updatedAuthor);
//    }
//
//    public void updateAuthorName(int authorId, String newName) {
//        Author updatedAuthor = authorDAO.findById(authorId);
//
//        updatedAuthor.setName(newName);
//
//        authorDAO.update(updatedAuthor);
//    }
