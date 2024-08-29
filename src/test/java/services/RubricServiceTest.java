package services;

import config.ConfigAppTest;
import dao.impl.RubricDAOImpl;
import domain.Rubric;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import repository.RubricRepository;
import service.CrudService;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Test cases for the RubricServiceImpl class.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitConfig(ConfigAppTest.class)
@WebAppConfiguration
@Sql(scripts = "classpath:truncate_all_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class RubricServiceTest {
    @Autowired
    private RubricDAOImpl rubricDAO;

    @Autowired
    private RubricRepository rubricRepository;

    @Autowired
    private CrudService<Rubric> rubricService;

    private Rubric createRubric() {
        Rubric rubric = Rubric.builder()
                .name("Technology")
                .build();
        return rubric;
    }

    @Test
    public void shouldInsertRubric() {
        Rubric rubric = createRubric();
        rubricService.insert(rubric);

        Rubric savedRubric = rubricRepository.findById(1);
        assertNotNull("Rubric should be saved and not null", savedRubric);
        assertEquals("Rubric name should be 'Technology'", "Technology", savedRubric.getName());
    }

    @Test
    public void shouldUpdateRubric() {
        Rubric rubric = createRubric();
        rubricRepository.save(rubric);

        rubric.setName("Medicine");
        rubricService.update(rubric);

        Rubric updatedRubric = rubricRepository.findById(1);
        assertNotNull("Updated rubric should not be null", updatedRubric);
        assertEquals("Updated rubric name should be 'Medicine'", "Medicine", updatedRubric.getName());
    }

    @Test
    public void shouldDeleteRubricById() {
        Rubric rubric = createRubric();
        rubricRepository.save(rubric);
        rubricService.deleteById(1);

        Rubric deletedRubric = rubricRepository.findById(1);
        assertNull("Deleted rubric should be null", deletedRubric);
    }

    @Test
    public void shouldFindRubricById() {
        Rubric rubric = createRubric();
        Rubric foundRubric = rubricService.findById(rubric.getId());

        assertNotNull("Rubric should be found and not null", foundRubric);
        assertEquals("Rubric name should be 'Technology'", "Technology", foundRubric.getName());
    }
}
