package uz.pdp.controller.userController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.User;
import uz.pdp.model.User;
import uz.pdp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;


    @GetMapping(path = "/home")
    public String getCourses() {

        return "/user/home";
    }

    @GetMapping("/settings")
    public String editUserPrifile(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = (int) session.getAttribute("userId");
        model.addAttribute("user", userService.getUserById(String.valueOf(userId)));
        return "user/editUsersProfile";
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

        return "redirect:/user/home";
    }


}
