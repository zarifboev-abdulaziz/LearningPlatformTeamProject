package uz.pdp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.pdp.model.Course;
import uz.pdp.model.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@Component
public class CourseDao {
    @Autowired
    public SessionFactory sessionFactory;


    public List<Course> getAllCoursesFromDb() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery< Course > criteriaQuery = criteriaBuilder.createQuery(Course.class);
        Root< Course > root = criteriaQuery.from(Course.class);
        criteriaQuery.select(root);
        javax.persistence.Query query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public void saveCourseToDb(Course course) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(course);

    }
}
