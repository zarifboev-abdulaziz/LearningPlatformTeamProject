package uz.pdp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.pdp.model.Course;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import static uz.pdp.constants.Constants.*;

@Component
public class CourseDao {
    @Autowired
    public SessionFactory sessionFactory;


    public List<Course> getAllCoursesFromDb(Integer page) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery< Course > criteriaQuery = criteriaBuilder.createQuery(Course.class);
        Root< Course > root = criteriaQuery.from(Course.class);
        criteriaQuery.select(root);
        javax.persistence.Query query = session.createQuery(criteriaQuery);
        List<Course> resultList = (List<Course>) query.getResultList();

        List<Course> courseList = new ArrayList<>();
        for (int i = (page - 1) * courseNumberPerPage; i < resultList.size() && i < (page*courseNumberPerPage); i++) {
            courseList.add(resultList.get(i));
        }

        return courseList;
    }

    public void saveCourseToDb(Course course) {
        Session currentSession = sessionFactory.getCurrentSession();
        if (course.getId() == null){
            currentSession.save(course);
        } else {
            currentSession.update(course);
        }
    }

    public Integer getTotalPages() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery< Course > criteriaQuery = criteriaBuilder.createQuery(Course.class);
        Root< Course > root = criteriaQuery.from(Course.class);
        criteriaQuery.select(root);
        javax.persistence.Query query = session.createQuery(criteriaQuery);
        List<Course> resultList = (List<Course>) query.getResultList();

        int size = resultList.size();

        if (size % courseNumberPerPage == 0){
            size = size/courseNumberPerPage;
        } else {
            size = size/courseNumberPerPage + 1;
        }

        return size;
    }

    public Course getCourseById(Integer courseId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Course course = currentSession.get(Course.class, courseId);
        return course;
    }

    public void deleteCourseById(Integer courseId) {
        Session session = sessionFactory.getCurrentSession();
        Query query=session.createQuery("delete from courses where id=" + courseId);
        query.executeUpdate();
    }

    public List<Course> getAllCoursesFromDb() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery< Course > criteriaQuery = criteriaBuilder.createQuery(Course.class);
        Root< Course > root = criteriaQuery.from(Course.class);
        criteriaQuery.select(root);
        javax.persistence.Query query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
