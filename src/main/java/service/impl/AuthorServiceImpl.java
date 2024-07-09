package service.impl;

import dao.CrudDAO;
import domain.Author;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import service.CrudService;

@Repository
@AllArgsConstructor
public class AuthorServiceImpl implements CrudService<Author> {
    CrudDAO<Author> authorDAO;

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

