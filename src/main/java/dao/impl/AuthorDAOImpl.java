package dao.impl;


import dao.CrudDAO;
import domain.Author;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import repository.*;


@Repository
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class AuthorDAOImpl implements CrudDAO<Author> {
    AuthorRepository authorRepository;
    SubscriptionRepository subscriptionRepository;
    AdRepository adRepository;

    @Override
    public void insert(Author author) {
        author.getPhones().forEach(phone -> phone.setAuthor(author));
        author.getEmail().setAuthor(author);
        author.getAddress().setAuthor(author);
        authorRepository.save(author);
    }

    @Override
    public void update(Author author) {
        authorRepository.save(author);
    }

    @Override
    public Author findById(int id) {
        return authorRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        adRepository.deleteAllByAuthorId(id);
        subscriptionRepository.deleteByAuthorId(id);
        authorRepository.deleteById(id);
    }
}
