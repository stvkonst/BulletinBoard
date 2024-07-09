package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.ConfigAppTest;
import controller.RubricController;
import domain.Rubric;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import service.CrudService;

/**
 * Unit tests for {@link RubricController}.
 * These tests use Spring MVC Test framework and Mockito for mocking dependencies.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigAppTest.class)
@WebAppConfiguration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RubricControllerTest {

    /**
     * Object mapper for JSON serialization/deserialization.
     */
    public static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * Mock MVC instance for simulating HTTP requests.
     */
    MockMvc mockMvc;

    /**
     * Mocked instance of CrudService for controller testing.
     */
    @Mock
    CrudService<Rubric> rubricService;

    /**
     * Controller instance being tested.
     */
    @InjectMocks
    RubricController rubricController;

    /**
     * Setup method to initialize mocks and MockMvc instance before each test.
     */
    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders
                .standaloneSetup(rubricController)
                .build();
    }

    /**
     * Test case for saving a rubric via POST request.
     *
     * @throws Exception if there is an error during the test execution
     */
    @Test
    public void shouldSaveRubric() throws Exception {
        // Create test data
        Rubric rubric = Rubric.builder()
                .name("Auto")
                .build();

        String json = MAPPER.writeValueAsString(rubric);

        // Mock the service call
        Mockito.doNothing().when(rubricService).insert(ArgumentMatchers.any(Rubric.class));

        // Perform the HTTP POST request and validate the response
        mockMvc
                .perform(MockMvcRequestBuilders.post("/rubric/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    /**
     * Test case for finding a rubric by ID via GET request.
     *
     * @throws Exception if there is an error during the test execution
     */
    @Test
    public void shouldFindById() throws Exception {
        // Create test data
        Rubric rubric = Rubric.builder()
                .name("Auto")
                .build();

        // Mock the service call
        Mockito.when(rubricService.findById(ArgumentMatchers.anyInt())).thenReturn(rubric);

        // Perform the HTTP GET request and validate the response
        mockMvc
                .perform(MockMvcRequestBuilders.get("/rubric/find/{id}", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Auto"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    /**
     * Test case for updating a rubric via PUT request.
     *
     * @throws Exception if there is an error during the test execution
     */
    @Test
    public void shouldUpdateRubric() throws Exception {
        // Create test data
        Rubric rubric = Rubric.builder()
                .name("Auto")
                .build();

        String json = MAPPER.writeValueAsString(rubric);

        // Mock the service call
        Mockito.doNothing().when(rubricService).update(ArgumentMatchers.any(Rubric.class));

        // Perform the HTTP PUT request and validate the response
        mockMvc
                .perform(MockMvcRequestBuilders.put("/rubric/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    /**
     * Test case for deleting a rubric by ID via DELETE request.
     *
     * @throws Exception if there is an error during the test execution
     */
    @Test
    public void shouldDeleteById() throws Exception {
        Mockito.doNothing().when(rubricService).deleteById(ArgumentMatchers.anyInt());

        // Perform the HTTP DELETE request and validate the response
        mockMvc
                .perform(MockMvcRequestBuilders.delete("/rubric/delete/{id}", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}