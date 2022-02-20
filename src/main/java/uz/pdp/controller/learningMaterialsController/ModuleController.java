package uz.pdp.controller.learningMaterialsController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dao.ModuleDao;
import uz.pdp.model.Course;
import uz.pdp.model.Module;
import uz.pdp.service.CourseService;
import uz.pdp.service.ModuleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/modules")
public class ModuleController {
    @Autowired
    CourseService courseService;
    @Autowired
    ModuleService moduleService;


    @GetMapping(path = "/addModule")
    public String addModuleForm() throws IOException {
        return "/module/addModuleForm";
    }

    @PostMapping(path = "/addModule")
    public String addModule(@ModelAttribute Module module, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        Integer lastCourseId = (Integer) session.getAttribute("lastCourseId");
        Course courseById = courseService.getCourseById(lastCourseId);
        module.setCourse(courseById);
        moduleService.saveModule(module);

        return "redirect:/courses/info/" + lastCourseId;
    }

    @GetMapping(path = "/editModule/{moduleId}")
    public String editModuleForm(@PathVariable Integer moduleId, Model model) throws IOException {
        Module module = moduleService.getModuleById(moduleId);

        model.addAttribute("module", module);
        return "/module/editModuleForm";
    }

    @GetMapping(path = "/delete/{moduleId}")
    public String deleteModule(@PathVariable Integer moduleId, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        Integer lastCourseId = (Integer) session.getAttribute("lastCourseId");
        moduleService.deleteModule(moduleId);

        return "redirect:/courses/info/" + lastCourseId;
    }

}
