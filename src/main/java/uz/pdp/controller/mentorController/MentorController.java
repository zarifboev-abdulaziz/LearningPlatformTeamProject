package uz.pdp.controller.mentorController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.Course;
import uz.pdp.model.Role;
import uz.pdp.model.User;
import uz.pdp.service.CourseService;
import uz.pdp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/mentor")
public class MentorController {
    @Autowired
    CourseService courseService;
    @Autowired
    UserService userService;


    @GetMapping("/show")
    public String showAllMentors(Model model) {
        List<User> mentors = userService.getMentors();
        model.addAttribute("mentors", mentors);
        return "/mentor/view-mentors";
    }

    @GetMapping("/add")
    public String addMentorForm() {
        return "/mentor/addMentorForm";
    }

    @PostMapping("/add")
    public String addMentor(HttpServletRequest request) {
        String id = request.getParameter("id");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User();
        if (id != null) {
            user.setId(Integer.parseInt(id));
        }
        user.setPassword(password);
        user.setEmail(email);
        user.setFullName(fullname);
        Role studentRole = new Role(1, "student");
        Role mentorRole = new Role(2, "mentor");
        user.setRoles(Arrays.asList(studentRole, mentorRole));
        userService.saveUser(user);
        return "redirect:/mentor/show";
    }

    @GetMapping("/edit/{mentorId}")
    public String editMentorForm(@PathVariable Integer mentorId, Model model) {
        User userById = userService.getUserById(String.valueOf(mentorId));
        model.addAttribute("mentor", userById);
        return "/mentor/editMentorForm";
    }

    @GetMapping("/delete/{mentorId}")
    public String deleteMentor(@PathVariable Integer mentorId) {
        userService.deleteUserById(mentorId);
        return "redirect:/mentor/show";
    }

    @GetMapping("/mentorInfo/{mentorId}")
    public String getInfoAboutMentor(Model model, @PathVariable Integer mentorId){
        User mentor = userService.getUserById(String.valueOf(mentorId));
        Map<Integer, Course> courseMap = new HashMap<>();
        for (Course cours : mentor.getCourses()) {
            courseMap.put(cours.getId(), cours);
        }

        model.addAttribute("mentor", mentor);
        model.addAttribute("mentorCourses", courseMap);
        return "/mentor/info";
    }

    @GetMapping("/home")
    public String mentorHome(Model model, HttpServletRequest request) throws IOException {
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

        model.addAttribute("allCourses", mentorCourses);
        return "/mentor/home";
    }

    @GetMapping("/courses")
    public String getMentorCourses(HttpServletRequest request, Model model) throws IOException {
        List<Course> mentorCourses = new ArrayList<>();
        List<Course> allCourses = courseService.getAllCourses();
        HttpSession session = request.getSession();
        Integer userId = (int) session.getAttribute("userId");

        for (Course course : allCourses) {
            for (User user : course.getUsers()) {
                if (user.getId() == userId) {
                    for (Role role : user.getRoles()) {
                        if (role.getId() == 2){
                            mentorCourses.add(course);
                        }
                    }
                }
            }
        }
        model.addAttribute("mentorCourses", mentorCourses);


        return "/mentor/mentorCourses";
    }
}
