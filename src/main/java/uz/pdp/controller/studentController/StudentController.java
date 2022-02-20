package uz.pdp.controller.studentController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired


    @GetMapping(path = "/home")
    public String studentHome(){
        return "/student/home";
    }

//    activeCourses/1


    @GetMapping(path = "/activeCourses/{page}")
    public String showActiveCourses(@PathVariable Integer page){



        return "/student/activeCourses";
    }


}
