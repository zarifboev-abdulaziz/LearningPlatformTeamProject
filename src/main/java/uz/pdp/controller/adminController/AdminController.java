package uz.pdp.controller.adminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.Course;
import uz.pdp.service.CourseService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    CourseService courseService;

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

    //   /admin/courses/editForm/
    @GetMapping(path = "/courses/addCourseForm")
    public String addCourseForm() {
        // TODO: 2/18/2022 kurs qoshish formasiga yuborish kerak
        return "/admin/....";
    }

    //   /admin/courses/editForm/
    @PostMapping(path = "/courses/addCourse")
    public String addCourse() {
        // TODO: 2/18/2022 formadan kelgan malumotlarni post qilib ushlab olinadi va databasega
        // save qilinadi

        return "/admin/....";
    }

    @GetMapping(path = "/courses/editForm/{courseId}")
    public String editCourseForm(@PathVariable Integer courseId, Model model) {
        // TODO: 2/18/2022 getCourseById() metodi orqali edit qilinayotgan courseni olib kelish
        // kerak va buni addAtribut qilib formga uzatish kerak;

        return "/admin/....";
    }

    @PostMapping(path = "/courses/editForm")
    public String editCourse(Model model) {
        // TODO: 2/18/2022 edit course formni toldirgandan keyin shu yerga keladi va buni databasega save qilish kerak.

        return "/admin/view-courses";
    }

    @GetMapping(path = "/courses/delete/{courseId}")
    public String deleteCourse(@PathVariable Integer courseId, Model model) {
        // TODO: 2/18/2022 course id orqali courseni delete qilish kerak

        return "/admin/....";
    }


}
