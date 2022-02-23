package uz.pdp.controller.learningMaterialsController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.Lesson;
import uz.pdp.model.Module;
import uz.pdp.model.Option;
import uz.pdp.model.Task;
import uz.pdp.service.LessonService;
import uz.pdp.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    LessonService lessonService;
    @Autowired
    TaskService taskService;

    @GetMapping("/addTask")
    public String addTaskForm(){
        return "/task/addTaskForm";
    }

    @PostMapping("/addTask")
    public String addTask(@ModelAttribute Task task, HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer lastLessonId = (Integer) session.getAttribute("lastLessonId");
        Lesson lessonById = lessonService.getLessonById(lastLessonId);
        task.setLesson(lessonById);
        taskService.saveTask(task);
        return "redirect:/lessons/info/" + lastLessonId;
    }


    @GetMapping("/editForm/{taskId}")
    public String editTaskForm(@PathVariable Integer taskId, Model model){
        Task task = taskService.getTaskById(taskId);

        model.addAttribute("task", task);
        return "/task/editTaskForm";
    }

    @GetMapping("/delete/{taskId}")
    public String deleteTask(@PathVariable Integer taskId,  HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer lastLessonId = (Integer) session.getAttribute("lastLessonId");
        taskService.deleteTask(taskId);

        return "redirect:/lessons/info/" + lastLessonId;
    }

    @GetMapping(path = "/info/{taskId}")
    public String getCourseInfo(@PathVariable Integer taskId, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("lastTaskId", taskId);
        Task taskById = taskService.getTaskById(taskId);
        List<Option> options = taskById.getOptions();

        model.addAttribute("task", taskById);
        model.addAttribute("options", options);
        return "/task/taskInfoAndOptions";
    }

}
