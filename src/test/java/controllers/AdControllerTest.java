package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.ConfigAppTest;
import controller.AdController;
import domain.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import service.AdService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Unit tests for {@link AdController}.
 * These tests use Spring MVC Test framework and Mockito for mocking dependencies.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigAppTest.class)
@WebAppConfiguration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdControllerTest {
    /**
     * Object mapper for JSON serialization/deserialization.
     */
    public static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * Mock MVC instance for simulating HTTP requests.
     */
    MockMvc mockMvc;

    /**
     * Mocked instance of AdService for controller testing.
     */
    @Mock
    AdService adService;

    /**
     * Controller instance being tested.
     */
    @InjectMocks
    AdController adController;

    /**
     * Setup method to initialize mocks and MockMvc instance before each test.
     */
    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders
                .standaloneSetup(adController)
                .build();
    }

    /**
     * Test case for saving an advertisement via POST request.
     */
    @Test
    public void shouldSaveAd() throws Exception {
        // Create test data
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

        Rubric rubric = Rubric.builder()
                .name("Auto")
                .build();

        Ad ad = Ad.builder()
                .name("Sell Audi 100")
                .publicationDate(LocalDate.of(2023, 5, 21))
                .price(BigDecimal.valueOf(20000.00))
                .text("Sell Audi 100 in good condition")
                .active(true)
                .author(author)
                .rubric(rubric)
                .build();

        String json = MAPPER.writeValueAsString(ad);

        // Mock the service call
        Mockito.doNothing().when(adService).insert(ArgumentMatchers.any(Ad.class));

        // Perform the HTTP POST request and validate the response
        mockMvc
                .perform(MockMvcRequestBuilders.post("/ad/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    /**
     * Test case for finding all advertisements by a list of rubric IDs via GET request.
     */
    @Test
    public void shouldFindAllByRubricIds() throws Exception {
        // Create test data
        Address address1 = Address.builder()
                .name("Some street, 15")
                .build();

        Email email1 = Email.builder()
                .name("mail@mail.com")
                .build();

        Phone phone1 = Phone.builder()
                .name("050-555-66-77")
                .build();

        Author author1 = Author.builder()
                .name("John")
                .address(address1)
                .email(email1)
                .phones(List.of(phone1))
                .build();

        address1.setAuthor(author1);
        email1.setAuthor(author1);
        phone1.setAuthor(author1);

        Rubric rubric = Rubric.builder()
                .name("Auto")
                .build();

        Ad ad1 = Ad.builder()
                .id(1)
                .name("Sell Audi 100")
                .publicationDate(LocalDate.of(2023, 5, 21))
                .price(BigDecimal.valueOf(20000.00))
                .text("Sell Audi 100 in good condition")
                .active(true)
                .author(author1)
                .rubric(rubric)
                .build();

// ------------------------

        Address address2 = Address.builder()
                .name("Some street, 15")
                .build();

        Email email2 = Email.builder()
                .name("mail@mail.com")
                .build();

        Phone phone2 = Phone.builder()
                .name("050-555-66-77")
                .build();

        Author author2 = Author.builder()
                .name("John")
                .address(address2)
                .email(email2)
                .phones(List.of(phone2))
                .build();

        address2.setAuthor(author2);
        email2.setAuthor(author2);
        phone2.setAuthor(author2);


        Ad ad2 = Ad.builder()
                .id(2)
                .name("Sell VW Passat")
                .publicationDate(LocalDate.of(2023, 6, 10))
                .price(BigDecimal.valueOf(15000.00))
                .text("Sell VW Passat in good condition")
                .active(true)
                .author(author2)
                .rubric(rubric)
                .build();


        List<Ad> ads = List.of(ad1, ad2);

        // Mock the service call
        Mockito.when(adService.showByRubricIds(ArgumentMatchers.anyList())).thenReturn(ads);

        // Perform the HTTP GET request and validate the response
        mockMvc
                .perform(MockMvcRequestBuilders.get("/ad/find-all-by-rubric-ids/1,2"))
                .andDo(MockMvcResultHandlers.print())

                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Sell Audi 100"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(BigDecimal.valueOf(20000.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author.name").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Sell VW Passat"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price").value(BigDecimal.valueOf(15000.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].author.name").value("John"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    /**
     * Test case for finding all advertisements by author ID via GET request.
     */
    @Test
    public void shouldFindAllByAuthorId() throws Exception {
        // Create test data
        Address address1 = Address.builder()
                .name("Some street, 15")
                .build();

        Email email1 = Email.builder()
                .name("mail@mail.com")
                .build();

        Phone phone1 = Phone.builder()
                .name("050-555-66-77")
                .build();

        Author author1 = Author.builder()
                .name("John")
                .address(address1)
                .email(email1)
                .phones(List.of(phone1))
                .build();

        address1.setAuthor(author1);
        email1.setAuthor(author1);
        phone1.setAuthor(author1);

        Rubric rubric = Rubric.builder()
                .name("Auto")
                .build();

        Ad ad1 = Ad.builder()
                .id(1)
                .name("Sell Audi 100")
                .publicationDate(LocalDate.of(2023, 5, 21))
                .price(BigDecimal.valueOf(20000.00))
                .text("Sell Audi 100 in good condition")
                .active(true)
                .author(author1)
                .rubric(rubric)
                .build();

// ------------------------

        Address address2 = Address.builder()
                .name("Some street, 15")
                .build();

        Email email2 = Email.builder()
                .name("mail@mail.com")
                .build();

        Phone phone2 = Phone.builder()
                .name("050-555-66-77")
                .build();

        Author author2 = Author.builder()
                .name("John")
                .address(address2)
                .email(email2)
                .phones(List.of(phone2))
                .build();

        address2.setAuthor(author2);
        email2.setAuthor(author2);
        phone2.setAuthor(author2);


        Ad ad2 = Ad.builder()
                .id(2)
                .name("Sell VW Passat")
                .publicationDate(LocalDate.of(2023, 6, 10))
                .price(BigDecimal.valueOf(15000.00))
                .text("Sell VW Passat in good condition")
                .active(true)
                .author(author2)
                .rubric(rubric)
                .build();


        List<Ad> ads = List.of(ad1, ad2);

        // Mock the service call
        Mockito.when(adService.findAllByAuthorId(ArgumentMatchers.anyInt())).thenReturn(ads);

        // Perform the HTTP GET request and validate the response
        mockMvc
                .perform(MockMvcRequestBuilders.get("/ad/find-all-by-author/{id}", 1))
                .andDo(MockMvcResultHandlers.print())

                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Sell Audi 100"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(BigDecimal.valueOf(20000.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author.name").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Sell VW Passat"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price").value(BigDecimal.valueOf(15000.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].author.name").value("John"))
                .andExpect(MockMvcResultMatchers.status().is(200));


    }

    /**
     * Test case for finding all advertisements by a word in their name via GET request.
     */
    @Test
    public void shouldFindAllByWord() throws Exception {
        // Create test data
        Address address1 = Address.builder()
                .name("Some street, 15")
                .build();

        Email email1 = Email.builder()
                .name("mail@mail.com")
                .build();

        Phone phone1 = Phone.builder()
                .name("050-555-66-77")
                .build();

        Author author1 = Author.builder()
                .name("John")
                .address(address1)
                .email(email1)
                .phones(List.of(phone1))
                .build();

        address1.setAuthor(author1);
        email1.setAuthor(author1);
        phone1.setAuthor(author1);

        Rubric rubric = Rubric.builder()
                .name("Auto")
                .build();

        Ad ad1 = Ad.builder()
                .id(1)
                .name("Sell Audi 100")
                .publicationDate(LocalDate.of(2023, 5, 21))
                .price(BigDecimal.valueOf(20000.00))
                .text("Sell Audi 100 in good condition")
                .active(true)
                .author(author1)
                .rubric(rubric)
                .build();

// ------------------------

        Address address2 = Address.builder()
                .name("Some street, 15")
                .build();

        Email email2 = Email.builder()
                .name("mail@mail.com")
                .build();

        Phone phone2 = Phone.builder()
                .name("050-555-66-77")
                .build();

        Author author2 = Author.builder()
                .name("John")
                .address(address2)
                .email(email2)
                .phones(List.of(phone2))
                .build();

        address2.setAuthor(author2);
        email2.setAuthor(author2);
        phone2.setAuthor(author2);


        Ad ad2 = Ad.builder()
                .id(2)
                .name("Sell VW Passat")
                .publicationDate(LocalDate.of(2023, 6, 10))
                .price(BigDecimal.valueOf(15000.00))
                .text("Sell VW Passat in good condition")
                .active(true)
                .author(author2)
                .rubric(rubric)
                .build();


        List<Ad> ads = List.of(ad1, ad2);

        // Mock the service call
        Mockito.when(adService.findAllByNameContains(ArgumentMatchers.anyString())).thenReturn(ads);

        // Perform the HTTP GET request and validate the response
        mockMvc
                .perform(MockMvcRequestBuilders.get("/ad/find-all-by-word/{word}", "Sell"))
                .andDo(MockMvcResultHandlers.print())

                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Sell Audi 100"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(BigDecimal.valueOf(20000.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author.name").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Sell VW Passat"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price").value(BigDecimal.valueOf(15000.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].author.name").value("John"))
                .andExpect(MockMvcResultMatchers.status().is(200));


    }

    /**
     * Test case for finding all advertisements with pagination via GET request.
     */
    @Test
    public void shouldFindAllPageSize() throws Exception {
        // Create test data
        Address address1 = Address.builder()
                .name("Some street, 15")
                .build();

        Email email1 = Email.builder()
                .name("mail@mail.com")
                .build();

        Phone phone1 = Phone.builder()
                .name("050-555-66-77")
                .build();

        Author author1 = Author.builder()
                .name("John")
                .address(address1)
                .email(email1)
                .phones(List.of(phone1))
                .build();

        address1.setAuthor(author1);
        email1.setAuthor(author1);
        phone1.setAuthor(author1);

        Rubric rubric = Rubric.builder()
                .name("Auto")
                .build();

        Ad ad1 = Ad.builder()
                .id(1)
                .name("Sell Audi 100")
                .publicationDate(LocalDate.of(2023, 5, 21))
                .price(BigDecimal.valueOf(20000.00))
                .text("Sell Audi 100 in good condition")
                .active(true)
                .author(author1)
                .rubric(rubric)
                .build();

// ------------------------

        Address address2 = Address.builder()
                .name("Some street, 15")
                .build();

        Email email2 = Email.builder()
                .name("mail@mail.com")
                .build();

        Phone phone2 = Phone.builder()
                .name("050-555-66-77")
                .build();

        Author author2 = Author.builder()
                .name("John")
                .address(address2)
                .email(email2)
                .phones(List.of(phone2))
                .build();

        address2.setAuthor(author2);
        email2.setAuthor(author2);
        phone2.setAuthor(author2);


        Ad ad2 = Ad.builder()
                .id(2)
                .name("Sell VW Passat")
                .publicationDate(LocalDate.of(2023, 6, 10))
                .price(BigDecimal.valueOf(15000.00))
                .text("Sell VW Passat in good condition")
                .active(true)
                .author(author2)
                .rubric(rubric)
                .build();


        List<Ad> ads = List.of(ad1, ad2);

        // Mock the service call
        Mockito.when(adService.findAllPage(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())).thenReturn(ads);

        // Perform the HTTP GET request and validate the response
        mockMvc
                .perform(MockMvcRequestBuilders.get("/ad/find-all/{page}/{size}", 0, 1))
                .andDo(MockMvcResultHandlers.print())

                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Sell Audi 100"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(BigDecimal.valueOf(20000.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author.name").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Sell VW Passat"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price").value(BigDecimal.valueOf(15000.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].author.name").value("John"))
                .andExpect(MockMvcResultMatchers.status().is(200));


    }

    /**
     * Test case for finding an advertisement by its ID via GET request.
     */
    @Test
    public void shouldFindById() throws Exception {
        // Create test data
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

        Rubric rubric = Rubric.builder()
                .name("Auto")
                .build();

        Ad ad = Ad.builder()
                .id(1)
                .name("Sell Audi 100")
                .publicationDate(LocalDate.of(2023, 5, 21))
                .price(BigDecimal.valueOf(20000.00))
                .text("Sell Audi 100 in good condition")
                .active(true)
                .author(author)
                .rubric(rubric)
                .build();

        // Mock the service call
        Mockito.when(adService.findById(ArgumentMatchers.anyInt())).thenReturn(ad);

        // Perform the HTTP GET request and validate the response
        mockMvc
                .perform(MockMvcRequestBuilders.get("/ad/find/{id}", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Sell Audi 100"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(BigDecimal.valueOf(20000.00)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    /**
     * Test case for finding all advertisements by publication date via a GET request.
     */
    @Test
    public void shouldFindAllByDate() throws Exception {
        // Create test data
        Address address1 = Address.builder()
                .name("Some street, 15")
                .build();

        Email email1 = Email.builder()
                .name("mail@mail.com")
                .build();

        Phone phone1 = Phone.builder()
                .name("050-555-66-77")
                .build();

        Author author1 = Author.builder()
                .name("John")
                .address(address1)
                .email(email1)
                .phones(List.of(phone1))
                .build();

        address1.setAuthor(author1);
        email1.setAuthor(author1);
        phone1.setAuthor(author1);

        Rubric rubric = Rubric.builder()
                .name("Auto")
                .build();

        Ad ad1 = Ad.builder()
                .id(1)
                .name("Sell Audi 100")
                .publicationDate(LocalDate.of(2023, 5, 21))
                .price(BigDecimal.valueOf(20000.00))
                .text("Sell Audi 100 in good condition")
                .active(true)
                .author(author1)
                .rubric(rubric)
                .build();

// ------------------------

        Address address2 = Address.builder()
                .name("Some street, 15")
                .build();

        Email email2 = Email.builder()
                .name("mail@mail.com")
                .build();

        Phone phone2 = Phone.builder()
                .name("050-555-66-77")
                .build();

        Author author2 = Author.builder()
                .name("John")
                .address(address2)
                .email(email2)
                .phones(List.of(phone2))
                .build();

        address2.setAuthor(author2);
        email2.setAuthor(author2);
        phone2.setAuthor(author2);


        Ad ad2 = Ad.builder()
                .id(2)
                .name("Sell VW Passat")
                .publicationDate(LocalDate.of(2023, 6, 10))
                .price(BigDecimal.valueOf(15000.00))
                .text("Sell VW Passat in good condition")
                .active(true)
                .author(author2)
                .rubric(rubric)
                .build();


        List<Ad> ads = List.of(ad1, ad2);

        // Mock the service call
        Mockito.when(adService.findAllByPublicationDate(LocalDate.of(2023, 5, 21))).thenReturn(ads);

        // Perform the HTTP GET request and validate the response
        mockMvc
                .perform(MockMvcRequestBuilders.get("/ad/find-all-by-date/{date}", "2023-05-21"))
                .andDo(MockMvcResultHandlers.print())

                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Sell Audi 100"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(BigDecimal.valueOf(20000.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author.name").value("John"))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }

    /**
     * Test case for updating an advertisement via PUT request.
     */
    @Test
    public void shouldUpdateAd() throws Exception {
        // Create test data
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

        Rubric rubric = Rubric.builder()
                .name("Auto")
                .build();

        Ad ad = Ad.builder()
                .id(1)
                .name("Sell Audi 100")
                .publicationDate(LocalDate.of(2023, 5, 21))
                .price(BigDecimal.valueOf(20000.00))
                .text("Sell Audi 100 in good condition")
                .active(true)
                .author(author)
                .rubric(rubric)
                .build();


        String json = MAPPER.writeValueAsString(ad);

        // Mock the service call
        Mockito.doNothing().when(adService).update(ArgumentMatchers.any(Ad.class));

        // Perform the HTTP GET request and validate the response
        mockMvc
                .perform(MockMvcRequestBuilders.put("/ad/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    /**
     * Test case for deleting an advertisement by ID via DELETE request.
     */
    @Test
    public void shouldDeleteById() throws Exception {
        Mockito.doNothing().when(adService).deleteById(ArgumentMatchers.anyInt());

        mockMvc
                .perform(MockMvcRequestBuilders.delete("/ad/delete/{id}", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    /**
     * Test case for deleting all advertisements by author ID via DELETE request.
     */
    @Test
    public void shouldDeleteAllByAuthorId() throws Exception {
        Mockito.doNothing().when(adService).deleteAllByAuthorId(ArgumentMatchers.anyInt());

        mockMvc
                .perform(MockMvcRequestBuilders.delete("/ad/delete-all/{id}", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
