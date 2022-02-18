package uz.pdp.controller.adminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import uz.pdp.model.Course;
import uz.pdp.model.User;
import uz.pdp.service.CourseService;
import uz.pdp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    CourseService courseService;
    @Autowired
    UserService userService;

    @GetMapping(path = "/home")
    public String studentHome() {
        return "/admin/home";
    }

    @GetMapping(path = "/courses")
    public String getCoursesForAdmin(Model model) {
        List<Course> allCourses = courseService.getAllCourses();

        model.addAttribute("courseList", allCourses);
        return "/admin/view-courses";
    }

    @GetMapping(path = "/courses/addCourseForm")
    public String addCourseForm(Model model) {
        List<User> mentorList = userService.getMentors();

        model.addAttribute("mentorList", mentorList);
        return "/admin/addCourseForm";
    }

    @PostMapping(path = "/courses/addCourse")
    public String addCourse(HttpServletRequest request, @RequestParam(name = "file") CommonsMultipartFile file) throws IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        String[] mentors = request.getParameterValues("mentors");
        String active = request.getParameter("active");

        boolean isActive = true;
        if (active == null){
            isActive = false;
        }

        Course course = new Course();
        course.setName(name);
        course.setDescription(description);
        course.setPrice(Double.valueOf(price));
        course.setActive(isActive);
        courseService.saveCourse(course, file, mentors);

        return "redirect:/admin/courses";
    }

    @GetMapping(path = "/courses/editForm/{courseId}")
    public String editCourseForm(@PathVariable Integer courseId, Model model) {
        return "";
    }

    @PostMapping(path = "/courses/editForm")
    public String editCourse(Model model) {

        return "";
    }

    @GetMapping(path = "/courses/delete/{courseId}")
    public String deleteCourse(@PathVariable Integer courseId, Model model) {
        return "";
    }


}
