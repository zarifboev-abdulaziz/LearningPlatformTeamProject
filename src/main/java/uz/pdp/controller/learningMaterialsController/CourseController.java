package uz.pdp.controller.learningMaterialsController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.model.Course;
import uz.pdp.service.CourseService;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    CourseService courseService;


    @GetMapping()
    public String getCourses(Model model){
        List<Course> courseList = courseService.getAllCourses();
        model.addAttribute("courseList", courseList);
        return "/course/view-courses";
    }



}
