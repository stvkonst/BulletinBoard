package dao.impl;

import dao.AdDAO;
import dao.CrudDAO;
import domain.Rubric;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import repository.RubricRepository;


@Repository
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class RubricDAOImpl implements CrudDAO<Rubric> {
    RubricRepository rubricRepository;

    AdDAO adDAO;

    @Override
    public void insert(Rubric rubric) {
        rubricRepository.save(rubric);
    }

    @Override
    public void update(Rubric rubric) {
        rubricRepository.save(rubric);
    }

    @Override
    public void deleteById(int id) {
        adDAO.deleteAllByRubricId(id);
        rubricRepository.deleteById(id);
    }

    @Override
    public Rubric findById(int id) {
        return rubricRepository.findById(id);
    }
}
