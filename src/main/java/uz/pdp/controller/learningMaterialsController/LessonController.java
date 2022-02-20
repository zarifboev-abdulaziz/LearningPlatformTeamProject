package uz.pdp.controller.learningMaterialsController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.Course;
import uz.pdp.model.Lesson;
import uz.pdp.model.Module;
import uz.pdp.service.LessonService;
import uz.pdp.service.ModuleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/lessons")
public class LessonController {
    @Autowired
    ModuleService moduleService;
    @Autowired
    LessonService lessonService;

    @GetMapping(path = "/addLesson")
    public String addModuleForm() throws IOException {
        return "/lesson/addLessonForm";
    }

    @PostMapping(path = "/addLesson")
    public String addModule(@ModelAttribute Lesson lesson, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        Integer lastModuleId = (Integer) session.getAttribute("lastModuleId");
        Module moduleById = moduleService.getModuleById(lastModuleId);
        lesson.setModule(moduleById);
        lessonService.saveLesson(lesson);

        return "redirect:/modules/info/" + lastModuleId;
    }

    @GetMapping(path = "/editLesson/{lessonId}")
    public String editModuleForm(@PathVariable Integer lessonId, Model model) throws IOException {
        Lesson lesson = lessonService.getLessonById(lessonId);

        model.addAttribute("lesson", lesson);
        return "/lesson/editLessonForm";
    }

    @GetMapping(path = "/delete/{lessonId}")
    public String deleteModule(@PathVariable Integer lessonId, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        Integer lastModuleId = (Integer) session.getAttribute("lastModuleId");
        lessonService.deleteLesson(lessonId);

        return "redirect:/modules/info/" + lastModuleId;
    }

    @GetMapping(path = "/info/{lessonId}")
    public String getCourseInfo(@PathVariable Integer lessonId, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("lastLessonId", lessonId);
        Lesson lessonById = lessonService.getLessonById(lessonId);

        model.addAttribute("lesson", lessonById);
        return "/lesson/lessonInfo";
    }


}
