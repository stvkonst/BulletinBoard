package service.impl;


import dao.CrudDAO;
import dao.impl.RubricDAOImpl;
import domain.Rubric;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import service.CrudService;

@Repository
@AllArgsConstructor
public class RubricServiceImpl implements CrudService<Rubric> {
    CrudDAO<Rubric> rubricDAO;

    @Override
    public void insert(Rubric rubric) {
        rubricDAO.insert(rubric);
    }

    @Override
    public void update(Rubric rubric) {
        rubricDAO.update(rubric);
    }

    @Override
    public void deleteById(int id) {
        rubricDAO.deleteById(id);
    }

    @Override
    public Rubric findById(int id) {
        return rubricDAO.findById(id);
    }
}
