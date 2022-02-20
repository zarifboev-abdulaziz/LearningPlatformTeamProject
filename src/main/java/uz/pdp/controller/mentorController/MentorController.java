package uz.pdp.controller.mentorController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.model.Course;
import uz.pdp.model.User;
import uz.pdp.service.CourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/mentor")
public class MentorController {

    @Autowired
    CourseService courseService;
    
    @GetMapping("/home")
    public String mentorHome(){
        
        return "/mentor/home";
    }
    
    @GetMapping("/courses")
    public String getMentorCourses(HttpServletRequest request, Model model){
        List<Course> mentorCourses = new ArrayList<>();
        List<Course> allCourses = courseService.getAllCourses();
        HttpSession session = request.getSession();
        Integer userId = (int) session.getAttribute("userId");

        for (Course course : allCourses) {
            for (User user : course.getUsers()) {
                if (user.getId() == userId) {
                    mentorCourses.add(course);
                }
            }
        }
        model.addAttribute("mentorCourses", mentorCourses);
        
        
        return "/mentor/mentorCourses";
    }
}
