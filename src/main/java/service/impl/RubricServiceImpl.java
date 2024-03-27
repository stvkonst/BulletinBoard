package service.impl;


import dao.impl.RubricDAOImpl;
import domain.Rubric;
import org.springframework.stereotype.Repository;
import service.CrudService;

@Repository
public class RubricServiceImpl implements CrudService<Rubric> {
    RubricDAOImpl rubricDAO;

    public RubricServiceImpl() {
        this.rubricDAO = new RubricDAOImpl();
    }

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


    //    AnnotationConfigApplicationContext context =
//            new AnnotationConfigApplicationContext(ConfigApp.class);
//
//    RubricDAOImpl rubricDAO = context.getBean(RubricDAOImpl.class);
//
//    public void insertRubric(String name) {
//        Rubric rubric1 = Rubric.builder()
//                .name("Auto")
//                .build();
//
//        rubricDAO.insert(rubric1);
//    }
//
//    public void updateRubricName(int rubricId, String newName) {
//        Rubric updatedRubric = rubricDAO.findById(rubricId);
//
//        updatedRubric.setName(newName);
//
//        rubricDAO.update(updatedRubric);
//    }
//
//    public void deleteRubric(int rubricId) {
//        rubricDAO.deleteById(rubricId);
//    }
}
