package uz.pdp.controller.learningMaterialsController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.Lesson;
import uz.pdp.model.Module;
import uz.pdp.model.Option;
import uz.pdp.model.Task;
import uz.pdp.payload.TaskDto;
import uz.pdp.service.LessonService;
import uz.pdp.service.OptionService;
import uz.pdp.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    LessonService lessonService;
    @Autowired
    TaskService taskService;
    @Autowired
    OptionService optionService;

    @GetMapping("/addTask")
    public String addTaskForm(){
        return "/task/addTaskForm";
    }

    @PostMapping("/editTask")
    public String editTask(@ModelAttribute TaskDto taskDto, HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer lastLessonId = (Integer) session.getAttribute("lastLessonId");

        Task taskById = taskService.getTaskById(taskDto.getTaskId());
        taskById.setBody(taskDto.getBody());
        taskById.setTitle(taskDto.getTitle());

        for (Option option : taskById.getOptions()) {
            option.setRightAnswer(false);

            if (option.getId() == taskDto.getTrueOption()) {
                option.setRightAnswer(true);
            }

            optionService.saveOption(option);
        }

        taskService.saveTask(taskById);
        return "redirect:/lessons/info/" + lastLessonId;
    }

    @PostMapping("/addTask")
    public String addTask(@ModelAttribute TaskDto taskDto, HttpServletRequest request){

        HttpSession session = request.getSession();
        Integer lastLessonId = (Integer) session.getAttribute("lastLessonId");
        Lesson lessonById = lessonService.getLessonById(lastLessonId);

        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setBody(taskDto.getBody());

        task.setLesson(lessonById);
        Task savedTask = taskService.saveTask(task);

        List<String> options = new ArrayList<>(Arrays.asList(taskDto.getOption1(), taskDto.getOption2(), taskDto.getOption3(), taskDto.getOption4()));

        for (int i = 0; i < options.size(); i++) {
            Option savingOption = new Option();
            savingOption.setTask(savedTask);
            savingOption.setBody(options.get(i));

            if ((i+1) == taskDto.getTrueOption()){
                savingOption.setRightAnswer(true);
            } else {
                savingOption.setRightAnswer(false);
            }

            Option savedOption = optionService.saveOption(savingOption);
            savedTask.getOptions().add(savedOption);
        }

        taskService.saveTask(savedTask);
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
