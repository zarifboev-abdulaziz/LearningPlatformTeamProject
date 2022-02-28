package uz.pdp.controller.learningMaterialsController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import uz.pdp.model.Course;
import uz.pdp.model.Module;
import uz.pdp.model.User;
import uz.pdp.service.CourseService;
import uz.pdp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    UserService userService;


    @GetMapping(path = "/{page}")
    public String getCoursesForAdmin(@PathVariable Integer page, Model model, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        int roleId = (int)session.getAttribute("roleId");
        List<Course> allCourses = courseService.getAllCourses(page, roleId);
        Integer pages = courseService.getTotalPages(roleId);


        model.addAttribute("courseList", allCourses);
        model.addAttribute("pages", pages);
        return "/course/view-courses";
    }

    @GetMapping(path = "/addCourseForm")
    public String addCourseForm(Model model) {
        List<User> mentorList = userService.getMentors();

        model.addAttribute("mentorList", mentorList);
        return "/course/addCourseForm";
    }

    @PostMapping(path = "/addCourse")
    public String addCourse(HttpServletRequest request, @RequestParam(name = "file") CommonsMultipartFile file) throws IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        String[] mentors = request.getParameterValues("mentors");
        String active = request.getParameter("active");

        boolean isActive = true;
        if (active == null){
            isActive = false;
        }

        if (id == null){
            Course course = new Course();
            course.setName(name);
            course.setDescription(description);
            course.setPrice(Double.valueOf(price));
            course.setActive(isActive);
            courseService.saveCourse(course, file, mentors);
        } else {
            Course courseById = courseService.getCourseById(Integer.parseInt(id));
            courseById.setName(name);
            courseById.setDescription(description);
            courseById.setPrice(Double.valueOf(price));
            courseById.setActive(isActive);
            courseService.saveCourse(courseById, file, mentors);
        }

        return "redirect:/courses/1";
    }

    @GetMapping(path = "/editForm/{courseId}")
    public String editCourseForm(@PathVariable Integer courseId, Model model) {
        Course course = courseService.getCourseById(courseId);
        model.addAttribute("course", course);
        List<User> mentorList = userService.getMentors();

        model.addAttribute("mentorList", mentorList);
        return "/course/editCourseForm";
    }


    @GetMapping(path = "/delete/{courseId}")
    public String deleteCourse(@PathVariable Integer courseId, Model model) {
        courseService.deleteCourseById(courseId);
        return "redirect:/courses/1";
    }

    @GetMapping(path = "/info/{courseId}")
    public String getCourseInfo(@PathVariable Integer courseId, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("lastCourseId", courseId);
        Integer roleId = (int)session.getAttribute("roleId");
        Course courseById = courseService.getCourseById(courseId);
        List<User> users = courseById.getUsers();
        List<User> mentors = new ArrayList<>();
        for (User user : users) {
            if (user.getRoleId() == 2){
                mentors.add(user);
            }
        }

        model.addAttribute("course", courseById);
        model.addAttribute("mentors", mentors);
        model.addAttribute("roleId", roleId);
        return "/course/courseInfoAndModules";
    }



}
