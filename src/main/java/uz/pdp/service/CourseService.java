package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import uz.pdp.dao.CourseDao;
import uz.pdp.dao.UserDao;
import uz.pdp.model.Course;
import uz.pdp.model.User;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import static uz.pdp.constants.Constants.*;

@Service
public class CourseService {
    @Autowired
    CourseDao courseDao;
    @Autowired
    UserDao userDao;

    @Transactional
    public List<Course> getAllCourses() {
        return courseDao.getAllCoursesFromDb();
    }

    @Transactional
    public void saveCourse(Course course, CommonsMultipartFile file, String[] mentors) throws IOException {

        for (String mentorId : mentors) {
            User mentor = userDao.getUserById(mentorId);
            course.getUsers().add(mentor);
        }

        String filename=file.getOriginalFilename();

        try{
            byte barr[]=file.getBytes();

            BufferedOutputStream bout=new BufferedOutputStream(
                    new FileOutputStream(resourcePath+"/"+filename));
            bout.write(barr);
            bout.flush();
            bout.close();

        } catch (Exception e){}

        course.setImagePath(filename);

        courseDao.saveCourseToDb(course);

    }
}
