package uz.pdp.controller.userController;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.*;
import uz.pdp.model.User;
import uz.pdp.service.CourseService;
import uz.pdp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    CourseService courseService;


    @GetMapping(path = "/home")
    public String getCourses(Model model) throws IOException {
        List<Course> allCourses = courseService.getAllCourses(1, 1);

        model.addAttribute("allCourses", allCourses);
        return "/user/home";
    }

    @GetMapping("/profile")
    public String editUserPrifile(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = (int) session.getAttribute("userId");
        model.addAttribute("user", userService.getUserById(String.valueOf(userId)));
        return "user/editUsersProfile";
    }

    @GetMapping("/fillBalance1")
    public String fillBalance(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer userId = (int) session.getAttribute("userId");
        User userById = userService.getUserById(String.valueOf(userId));
        model.addAttribute("profile", userById);
        model.addAttribute("isFillingBalance", true);
        return "/user/profileSettings";
    }

    @GetMapping("/fillBalance")
    public String fillUserBalance(HttpServletRequest request){
        User user = new User();
        String fill = request.getParameter("fill");
        HttpSession session = request.getSession();
        Integer userId = (int) session.getAttribute("userId");
        user.setBalance(Double.valueOf(fill));
        user.setId(userId);
        userService.fillBalance(user);
        return "redirect:/user/settings";
    }



    @PostMapping("/edit")
    public String saveUsersInfo(Model model, HttpServletRequest request) {
        String id = request.getParameter("id");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPasswords = request.getParameter("confirmPassword");

        List<User> allusers = userService.getAllusers();
        for (User alluser : allusers) {
            if (alluser.getId() == Integer.parseInt(id)){
                alluser.setFullName(fullname);
                alluser.setEmail(email);
                alluser.setPassword(newPassword);
                userService.editUser(alluser);
            }
        }

        HttpSession session = request.getSession();
        int roleId = (int)session.getAttribute("roleId");

        switch (roleId){
            case 1: return "redirect:/student/home";
            case 2: return "redirect:/mentor/home";
            case 3: return "redirect:/admin/home";
            case 4: return "redirect:/superAdmin/home";
        }
        return "redirect:/user/home";
    }

    @GetMapping(path = "/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("userId", null);
        session.invalidate();
        return "redirect:/user/home";
    }


    @GetMapping(path = "/settings")
    public String profile(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer userId = (int) session.getAttribute("userId");
        User userById = userService.getUserById(String.valueOf(userId));
        model.addAttribute("profile", userById);
        return "/user/profileSettings";
    }


    @GetMapping(path = "/courseInfo/{courseId}")
    public String showCoursesInfo(@PathVariable Integer courseId, HttpServletRequest request, Model model) throws IOException {
        Course courseById = courseService.getCourseById(courseId);

        int lessonCount = 0;

        for (Module module : courseById.getModules()) {
            lessonCount += module.getLessons().size();
        }
        List<User> mentors = new ArrayList<>();
        for (User user : courseById.getUsers()) {
            for (Role role : user.getRoles()) {
                if (role.getId() == 2){
                    mentors.add(user);
                }
            }
        }


        model.addAttribute("course", courseById);
        model.addAttribute("lessonCount", lessonCount);
        model.addAttribute("mentors", mentors);
        return "/guest/courseInfo";
    }


}
