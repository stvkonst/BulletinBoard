package services;

import config.ConfigAppTest;
import domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import repository.AdRepository;
import repository.AuthorRepository;
import repository.RubricRepository;
import service.AdService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.*;

/**
 * Test cases for the AdServiceImpl class.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitConfig(ConfigAppTest.class)
@WebAppConfiguration
@Sql(scripts = "classpath:truncate_all_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AdServiceTest {
    /**
     * Service layer for managing advertisements.
     */
    @Autowired
    private AdService adService;

    /**
     * Repository for managing advertisements.
     */
    @Autowired
    AdRepository adRepository;

    /**
     * Repository for managing authors.
     */
    @Autowired
    AuthorRepository authorRepository;

    /**
     * Repository for managing rubrics.
     */
    @Autowired
    RubricRepository rubricRepository;

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

        // Save the author first
        authorRepository.save(author);

        return author;
    }

    /**
     * Helper method to create and save a Rubric entity.
     */
    private Rubric createAndSaveRubric() {
        Rubric rubric = Rubric.builder()
                .name("Auto")
                .build();

        // Save the rubric first
        rubricRepository.save(rubric);

        return rubric;
    }

    /**
     * Helper method to create an Ad entity with predefined data.
     */
    private Ad createAd(Author author, Rubric rubric) {
        return Ad.builder()
                .name("Sell Audi 100")
                .publicationDate(LocalDate.of(2023, 5, 21))
                .price(BigDecimal.valueOf(20000))
                .text("Sell Audi 100 in good condition")
                .active(true)
                .author(author)
                .rubric(rubric)
                .build();
    }

    /**
     * Helper method to create a second Ad entity with different data.
     */
    private Ad createAd2(Author author, Rubric rubric) {
        return Ad.builder()
                .name("Buy BMW 320")
                .publicationDate(LocalDate.of(2023, 6, 15))
                .price(BigDecimal.valueOf(30000))
                .text("Buy BMW 320 in excellent condition")
                .active(true)
                .author(author)
                .rubric(rubric)
                .build();
    }

    /**
     * Test case for saving an advertisement.
     */
    @Test
    public void shouldSaveAd() {
        Author author = createAndSaveAuthor();
        Rubric rubric = createAndSaveRubric();
        Ad ad = createAd(author, rubric);

        // Save the ad
        adService.insert(ad);

        // Fetch the ad by its ID and verify it was saved correctly
        Ad savedAd = adRepository.findById(1);
        assertNotNull("Ad should be saved and not null", savedAd);
        assertEquals("Ad name should be 'Sell Audi 100", "Sell Audi 100", savedAd.getName());
        assertEquals("Ad price should be 20000.00", BigDecimal.valueOf(20000.00).stripTrailingZeros(), savedAd.getPrice().stripTrailingZeros());
        assertEquals("Ad publication date should be 2023-05-21", LocalDate.of(2023, 5, 21), savedAd.getPublicationDate());
        assertTrue("Ad should be active", savedAd.isActive());
    }

    /**
     * Test case for updating an advertisement.
     */
    @Test
    public void shouldUpdateAd() {
        Author author = createAndSaveAuthor();
        Rubric rubric = createAndSaveRubric();
        Ad ad = createAd(author, rubric);

        // Save the ad using repository
        adRepository.save(ad);

        // Fetch the ad, update and save through the service
        Ad adToUpdate = adRepository.findById(1);
        assertNotNull("Ad to update should not be null", adToUpdate);

        adToUpdate.setName("Sell BMW 320");
        adToUpdate.setPrice(BigDecimal.valueOf(25000));
        adService.update(adToUpdate);

        // Fetch the ad and verify it was updated correctly
        Ad updatedAd = adRepository.findById(1);
        assertNotNull("Updated ad should not be null", updatedAd);
        assertEquals("Updated ad name should be 'Sell BMW 320'", "Sell BMW 320", updatedAd.getName());
        assertEquals("Updated ad price should be 25000.00", BigDecimal.valueOf(25000.00).stripTrailingZeros(), updatedAd.getPrice().stripTrailingZeros());
    }

    /**
     * Test case for deleting an advertisement by ID.
     */
    @Test
    public void shouldDeleteAdById() {
        Author author = createAndSaveAuthor();
        Rubric rubric = createAndSaveRubric();
        Ad ad = createAd(author, rubric);

        // Save the ad using repository
        adRepository.save(ad);

        // Delete the ad using service
        adService.deleteById(1);

        // Verify the ad was deleted
        Ad deletedAd = adRepository.findById(1);
        assertNull("Deleted ad should be null", deletedAd);
    }

    /**
     * Test case for finding an advertisement by ID.
     */
    @Test
    public void shouldFindAdById() {
        Author author = createAndSaveAuthor();
        Rubric rubric = createAndSaveRubric();
        Ad ad = createAd(author, rubric);

        // Save the ad using repository
        adRepository.save(ad);

        // Fetch the ad by its ID using service
        Ad foundAd = adService.findById(1);
        assertNotNull("Found ad should not be null", foundAd);
        assertEquals("Found ad ID should match", 1, foundAd.getId());
    }

    /**
     * Test case for deleting all advertisements by rubric ID.
     */
    @Test
    public void shouldDeleteAllAdsByRubricId() {
        Author author = createAndSaveAuthor();
        Rubric rubric = createAndSaveRubric();
        Ad ad1 = createAd(author, rubric);
        Ad ad2 = createAd2(author, rubric);

        // Save the ads using repository
        adRepository.save(ad1);
        adRepository.save(ad2);

        // Delete all ads by rubric ID using service
        adService.deleteAllByRubricId(rubric.getId());

        // Verify the ads were deleted
        assertTrue("No ads should be found for the given rubric ID", adRepository.findAllByRubricIdIn(List.of(rubric.getId())).isEmpty());
    }

    /**
     * Test case for deleting all advertisements by author ID.
     */
    @Test
    public void shouldDeleteAllAdsByAuthorId() {
        Author author = createAndSaveAuthor();
        Rubric rubric = createAndSaveRubric();
        Ad ad1 = createAd(author, rubric);
        Ad ad2 = createAd2(author, rubric);

        // Save the ads using repository
        adRepository.save(ad1);
        adRepository.save(ad2);

        // Delete all ads by author ID using service
        adService.deleteAllByAuthorId(author.getId());

        // Verify the ads were deleted
        assertTrue("No ads should be found for the given author ID", adRepository.findAllByAuthorId(author.getId()).isEmpty());
    }

    /**
     * Test case for showing advertisements by rubric IDs.
     */
    @Test
    public void shouldShowAdsByRubricIds() {
        Author author = createAndSaveAuthor();
        Rubric rubric1 = createAndSaveRubric();
        Rubric rubric2 = Rubric.builder().name("Real Estate").build();
        rubricRepository.save(rubric2);

        Ad ad1 = createAd(author, rubric1);
        Ad ad2 = createAd2(author, rubric2);

        // Save the ads using repository
        adRepository.save(ad1);
        adRepository.save(ad2);

        // Show ads by rubric IDs using service
        List<Ad> ads = adService.showByRubricIds(List.of(rubric1.getId(), rubric2.getId()));
        assertEquals("There should be 2 ads found", 2, ads.size());
    }

    /**
     * Test case for deleting inactive advertisements.
     */
    @Test
    public void shouldDeleteByIsActiveIsFalse() {
        Author author = createAndSaveAuthor();
        Rubric rubric = createAndSaveRubric();
        Ad ad = createAd(author, rubric);

        // Save the ad using repository
        adRepository.save(ad);

        // Update the ad to inactive
        ad.setActive(false);
        adRepository.save(ad);
        //adRepository.flush();

        // Delete inactive ads using service
        adService.deleteByIsActiveIsFalse();

        // Verify the ad was deleted
        Optional<Ad> deletedAdOptional = Optional.ofNullable(adRepository.findById(1));
        assertFalse("Deleted inactive ad should not be present", deletedAdOptional.isPresent());
    }

    /**
     * Test case for finding all advertisements by author ID.
     */
    @Test
    public void shouldFindAllAdsByAuthorId() {
        Author author = createAndSaveAuthor();
        Rubric rubric = createAndSaveRubric();
        Ad ad1 = createAd(author, rubric);
        Ad ad2 = createAd2(author, rubric);

        // Save the ads using repository
        adRepository.save(ad1);
        adRepository.save(ad2);

        // Find all ads by author ID using service
        List<Ad> ads = adService.findAllByAuthorId(author.getId());
        assertEquals("There should be 2 ads found for the given author ID", 2, ads.size());
    }

    /**
     * Test case for finding all advertisements by name contains.
     */
    @Test
    public void shouldFindAllAdsByNameContains() {
        Author author = createAndSaveAuthor();
        Rubric rubric = createAndSaveRubric();
        Ad ad1 = createAd(author, rubric);
        ad1.setName("Sell Audi 100");
        Ad ad2 = createAd2(author, rubric);
        ad2.setName("Sell BMW 320");

        // Save the ads using repository
        adRepository.save(ad1);
        adRepository.save(ad2);

        // Find all ads by name contains using service
        List<Ad> ads = adService.findAllByNameContains("Sell");
        assertEquals("There should be 2 ads found containing 'Sell'", 2, ads.size());
    }

    /**
     * Test case for finding all advertisements by publication date.
     */
    @Test
    public void shouldFindAllAdsByPublicationDate() {
        Author author = createAndSaveAuthor();
        Rubric rubric = createAndSaveRubric();
        Ad ad1 = createAd(author, rubric);
        Ad ad2 = createAd2(author, rubric);
        ad2.setPublicationDate(LocalDate.of(2023, 5, 21));

        // Save the ads using repository
        adRepository.save(ad1);
        adRepository.save(ad2);

        // Find all ads by publication date using service
        List<Ad> ads = adService.findAllByPublicationDate(LocalDate.of(2023, 5, 21));
        assertEquals("There should be 2 ads found for the given publication date", 2, ads.size());
    }

    /**
     * Test case for finding all advertisements by page.
     */
    @Test
    public void shouldFindAllPage() {
        Author author = createAndSaveAuthor();
        Rubric rubric = createAndSaveRubric();
        Ad ad1 = createAd(author, rubric);
        Ad ad2 = createAd2(author, rubric);

        // Save the ads using repository
        adRepository.save(ad1);
        adRepository.save(ad2);

        // Find all ads by page using service
        List<Ad> ads = adService.findAllPage(0, 2);
        assertEquals("There should be 2 ads found in the first page", 2, ads.size());
    }

}