package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.ConfigAppTest;
import controller.SubscriptionController;
import domain.Subscription;
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
import service.SubscriptionService;

/**
 * Unit tests for {@link SubscriptionController}.
 * These tests use Spring MVC Test framework and Mockito for mocking dependencies.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigAppTest.class)
@WebAppConfiguration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubscriptionControllerTest {

    /**
     * Object mapper for JSON serialization/deserialization.
     */
    public static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * Mock MVC instance for simulating HTTP requests.
     */
    MockMvc mockMvc;

    /**
     * Mocked instance of SubscriptionService for controller testing.
     */
    @Mock
    SubscriptionService subService;

    /**
     * Controller instance being tested.
     */
    @InjectMocks
    SubscriptionController subscriptionController;

    /**
     * Setup method to initialize mocks and MockMvc instance before each test.
     */
    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders
                .standaloneSetup(subscriptionController)
                .build();
    }

    /**
     * Test case for saving a subscription via POST request.
     *
     * @throws Exception if there is an error during the test execution
     */
    @Test
    public void shouldSaveSubscription() throws Exception {
        // Create test data
        Subscription subscription = Subscription.builder()
                .name("Weekly Newsletter")
                .build();

        String json = MAPPER.writeValueAsString(subscription);

        // Mock the service call
        Mockito.doNothing().when(subService).insert(ArgumentMatchers.any(Subscription.class));

        // Perform the HTTP POST request and validate the response
        mockMvc
                .perform(MockMvcRequestBuilders.post("/subscription/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    /**
     * Test case for finding a subscription by ID via GET request.
     *
     * @throws Exception if there is an error during the test execution
     */
    @Test
    public void shouldFindById() throws Exception {
        // Create test data
        Subscription subscription = Subscription.builder()
                .name("Weekly Newsletter")
                .build();

        // Mock the service call
        Mockito.when(subService.findById(ArgumentMatchers.anyInt())).thenReturn(subscription);

        // Perform the HTTP GET request and validate the response
        mockMvc
                .perform(MockMvcRequestBuilders.get("/subscription/find/{id}", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Weekly Newsletter"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    /**
     * Test case for updating a subscription via PUT request.
     *
     * @throws Exception if there is an error during the test execution
     */
    @Test
    public void shouldUpdateSubscription() throws Exception {
        // Create test data
        Subscription subscription = Subscription.builder()
                .name("Weekly Newsletter")
                .build();

        String json = MAPPER.writeValueAsString(subscription);

        // Mock the service call
        Mockito.doNothing().when(subService).update(ArgumentMatchers.any(Subscription.class));

        // Perform the HTTP PUT request and validate the response
        mockMvc
                .perform(MockMvcRequestBuilders.put("/subscription/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    /**
     * Test case for deleting a subscription by ID via DELETE request.
     *
     * @throws Exception if there is an error during the test execution
     */
    @Test
    public void shouldDeleteById() throws Exception {
        Mockito.doNothing().when(subService).deleteById(ArgumentMatchers.anyInt());

        // Perform the HTTP DELETE request and validate the response
        mockMvc
                .perform(MockMvcRequestBuilders.delete("/subscription/delete/{id}", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}