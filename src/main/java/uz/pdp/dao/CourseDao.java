package uz.pdp.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.helper.Helper;
import uz.pdp.model.Course;

import java.util.List;

@Component
public class CourseDao {
    @Autowired
    JdbcTemplate template;


    public List<Course> getAllCoursesFromDb() {
        Session session = Helper.getSession();
        Transaction transaction = session.beginTransaction();

        Query from_courses = session.createQuery("from courses");
        List<Course> list = (List<Course>) from_courses.list();

        transaction.commit();
        return list;
    }
}
