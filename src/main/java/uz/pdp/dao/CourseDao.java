package uz.pdp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.model.Course;
import uz.pdp.model.Module;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static uz.pdp.constants.Constants.*;

@Component
public class CourseDao {
    @Autowired
    public SessionFactory sessionFactory;
    @Autowired
    JdbcTemplate template;


    public List<Course> getAllCoursesFromDb(Integer page, int roleId) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
        Root<Course> root = criteriaQuery.from(Course.class);
        criteriaQuery.select(root);
        javax.persistence.Query query = session.createQuery(criteriaQuery);
        List<Course> resultList = (List<Course>) query.getResultList();

        List<Course> courseList = new ArrayList<>();
        if (roleId == 1) {
            for (int i = (page - 1) * courseNumberPerPage; i < resultList.size() && i < (page * courseNumberPerPage); i++) {
                if (resultList.get(i).isActive()){
                    courseList.add(resultList.get(i));
                }
            }
        } else {
            for (int i = (page - 1) * courseNumberPerPage; i < resultList.size() && i < (page * courseNumberPerPage); i++) {
                courseList.add(resultList.get(i));
            }
        }


        return courseList;
    }

    public void saveCourseToDb(Course course) {
        Session currentSession = sessionFactory.getCurrentSession();
        if (course.getId() == null) {
            currentSession.save(course);
        } else {
            currentSession.update(course);
        }
    }

    public Integer getTotalPages(int roleId) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
        Root<Course> root = criteriaQuery.from(Course.class);
        criteriaQuery.select(root);
        javax.persistence.Query query = session.createQuery(criteriaQuery);
        List<Course> resultList = (List<Course>) query.getResultList();

        int size;
        if (roleId == 1){
            int count = 0;
            for (Course course : resultList) {
                if (course.isActive()){
                    count++;
                }
            }
            size = count;
        } else {
            size = resultList.size();
        }


        if (size % courseNumberPerPage == 0) {
            size = size / courseNumberPerPage;
        } else {
            size = size / courseNumberPerPage + 1;
        }

        return size;
    }

    public Course getCourseById(Integer courseId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Course course = currentSession.get(Course.class, courseId);
        Map<Integer, Module> moduleMap = new HashMap<>();
        for (Module module : course.getModules()) {
            moduleMap.put(module.getId(), module);
        }
        course.getModules().removeAll(course.getModules());
        for (Module value : moduleMap.values()) {
            course.getModules().add(value);
        }
        return course;
    }

    public void deleteCourseById(Integer courseId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from courses where id=" + courseId);
        query.executeUpdate();
    }

    public List<Course> getAllCoursesFromDb() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
        Root<Course> root = criteriaQuery.from(Course.class);
        criteriaQuery.select(root);
        javax.persistence.Query query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public void purchaseCourse(int userId, Integer courseId) {
        String query = "Select * from student_purchase_course("+userId+", "+courseId+")";

        Boolean isPurchased = template.queryForObject(query, (rs, rowNum) -> rs.getBoolean(1));

    }
}
