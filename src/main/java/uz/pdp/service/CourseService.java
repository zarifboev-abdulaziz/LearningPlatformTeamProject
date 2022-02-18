package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.CourseDao;
import uz.pdp.model.Course;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseDao courseDao;

    public List<Course> getAllCourses() {
        return courseDao.getAllCoursesFromDb();
    }
}
