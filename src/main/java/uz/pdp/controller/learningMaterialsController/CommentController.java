package uz.pdp.controller.learningMaterialsController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.service.CommentService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping(path = "/lesson/{lessonId}")
    public String addLessonComment(@PathVariable Integer lessonId, HttpServletRequest request){
        String comment = request.getParameter("comment");
        int userId = (int) request.getSession().getAttribute("userId");
        commentService.addLessonComment(userId, lessonId, comment);

        return "redirect:/student/lessonInfo/"+lessonId;
    }

    @PostMapping(path = "/task/{taskId}")
    public String addTaskComment(@PathVariable Integer taskId, HttpServletRequest request){
        String comment = request.getParameter("comment");
        int userId = (int) request.getSession().getAttribute("userId");
        int lastLessonId = (int) request.getSession().getAttribute("lastLessonId");
        commentService.addTaskComment(userId, taskId, comment);

        return "redirect:/student/tasks/"+lastLessonId+"/" + taskId;
    }

}
