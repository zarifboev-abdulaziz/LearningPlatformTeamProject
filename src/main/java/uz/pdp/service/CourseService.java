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
    public List<Course> getAllCourses(Integer page) throws IOException {
        List<Course> allCoursesFromDb = courseDao.getAllCoursesFromDb(page);

        for (Course course : allCoursesFromDb) {

            BufferedImage image =  ImageIO.read(new File(resourcePath + "/" + course.getImageName()));

            ByteArrayOutputStream base = new ByteArrayOutputStream();
            ImageIO.write(image,"png",base);
            base.flush();
            byte[] imageInByteArray = base.toByteArray();
            base.close();

            String b64 = DatatypeConverter.printBase64Binary(imageInByteArray);

            course.setImagePath(b64);
        }

        return allCoursesFromDb;
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

        course.setImageName(filename);

        courseDao.saveCourseToDb(course);

    }

    @Transactional
    public Integer getTotalPages() {
        return courseDao.getTotalPages();
    }

    @Transactional
    public Course getCourseById(Integer courseId) {
        return courseDao.getCourseById(courseId);
    }

    @Transactional
    public void deleteCourseById(Integer courseId) {
        courseDao.deleteCourseById(courseId);
    }

    @Transactional
    public List<Course> getAllCourses() {
       return courseDao.getAllCoursesFromDb();
    }
}
