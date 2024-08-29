package services;

import config.ConfigAppTest;
import dao.CrudDAO;
import dao.impl.AuthorDAOImpl;
import domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import repository.AuthorRepository;
import service.CrudService;
import service.impl.AuthorServiceImpl;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Test cases for the AuthorServiceImpl class.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitConfig(ConfigAppTest.class)
@WebAppConfiguration
@Sql(scripts = "classpath:truncate_all_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AuthorServiceTest {
    /**
     * Repository for managing authors.
     */
    @Autowired
    private AuthorRepository authorRepository;

    /**
     * Service layer for managing authors.
     */
    @Autowired
    private CrudService<Author> authorService;

    /**
     * Helper method to create and save an Author entity.
     */
    private Author createAndSaveAuthor() {
        Address address = Address.builder()
                .name("Some street, 15")
                .build();

        Email email = Email.builder()
                .name("mail@mail.com")
                .build();

        Phone phone = Phone.builder()
                .name("050-555-66-77")
                .build();

        Author author = Author.builder()
                .name("John")
                .address(address)
                .email(email)
                .phones(List.of(phone))
                .build();

        address.setAuthor(author);
        email.setAuthor(author);
        phone.setAuthor(author);

        authorRepository.save(author);

        return author;
    }

    /**
     * Test case for saving an author.
     */
    @Test
    public void shouldSaveAuthor() {
        Author author = createAndSaveAuthor();
        authorService.insert(author);
        Author savedAuthor = authorRepository.findById(1);

        assertNotNull("Author should be saved and not null", savedAuthor);
        assertEquals("Author name should be 'John'", "John", savedAuthor.getName());
        assertEquals("Some street, 15", savedAuthor.getAddress().getName());
        assertEquals("mail@mail.com", savedAuthor.getEmail().getName());
        assertEquals("050-555-66-77", savedAuthor.getPhones().get(0).getName());
    }

    /**
     * Test case for updating an advertisement.
     */
    @Test
    public void shouldUpdateAuthor() {
        Author author = createAndSaveAuthor();
        authorRepository.save(author);

        // Fetch the author, update and save through the repository
        Author authorToUpdate = authorRepository.findById(1);
        assertNotNull("Author to update should not be null", authorToUpdate);

        authorToUpdate.setName("Bob");
        authorService.update(authorToUpdate);

        // Fetch the author and verify it was updated correctly
        Author updatedAuthor = authorRepository.findById(1);
        assertNotNull("Updated ad should not be null", updatedAuthor);
        assertEquals("Updated author name should be 'Bob'", "Bob", updatedAuthor.getName());
    }

    /**
     * Test case for deleting an author by ID.
     */
    @Test
    public void shouldDeleteAuthorById() {
        Author author = createAndSaveAuthor();
        // Delete the author using service
        authorService.deleteById(1);
        // Verify the author was deleted
        Author deletedAuthor = authorRepository.findById(1);
        assertNull("Deleted ad should be null", deletedAuthor);
    }

    /**
     * Test case for finding an author by ID.
     */
    @Test
    public void shouldFindAuthor() {
        Author author = createAndSaveAuthor();
        authorRepository.save(author);
        Author savedAuthor = authorService.findById(1);

        assertNotNull("Author should be saved and not null", savedAuthor);
        assertEquals("Author name should be 'John'", "John", savedAuthor.getName());
        assertEquals("Some street, 15", savedAuthor.getAddress().getName());
        assertEquals("mail@mail.com", savedAuthor.getEmail().getName());
        assertEquals("050-555-66-77", savedAuthor.getPhones().get(0).getName());
    }
}
