package uz.pdp.controller.learningMaterialsController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dao.OptionDao;
import uz.pdp.model.Lesson;
import uz.pdp.model.Module;
import uz.pdp.model.Option;
import uz.pdp.model.Task;
import uz.pdp.service.OptionService;
import uz.pdp.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/options")
public class OptionController {
    @Autowired
    TaskService taskService;
    @Autowired
    OptionService optionService;

    @GetMapping("/addOption")
    public String addTaskForm(){
        return "/option/addOptionForm";
    }

    @PostMapping("/addOption")
    public String addOption(HttpServletRequest request, @ModelAttribute Option option){
        HttpSession session = request.getSession();
        Integer lastTaskId = (Integer) session.getAttribute("lastTaskId");
        Task taskById = taskService.getTaskById(lastTaskId);
        option.setTask(taskById);

        optionService.saveOption(option);
        return "redirect:/tasks/info/" + lastTaskId;
    }

    @GetMapping("/editForm/{optionId}")
    public String editTaskForm(@PathVariable Integer optionId, Model model){
        Option option = optionService.getOptionById(optionId);

        model.addAttribute("option", option);
        return "/option/editOptionForm";
    }

    @GetMapping("/delete/{optionId}")
    public String deleteTask(@PathVariable Integer optionId,  HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer lastTaskId = (Integer) session.getAttribute("lastTaskId");

        optionService.deleteOption(optionId);
        return "redirect:/tasks/info/" + lastTaskId;
    }


}
