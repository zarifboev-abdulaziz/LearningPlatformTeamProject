package uz.pdp.controller.learningMaterialsController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.helper.LessonComment;
import uz.pdp.model.Lesson;
import uz.pdp.service.CommentService;
import uz.pdp.service.LessonService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    LessonService lessonService;

    @PostMapping(path = "/lesson/{lessonId}")
    public String addLessonComment(@PathVariable Integer lessonId, HttpServletRequest request){
        String comment = request.getParameter("comment");
        int userId = (int) request.getSession().getAttribute("userId");
        commentService.addLessonComment(userId, lessonId, comment);

        return "redirect:/student/lessonInfo/"+lessonId;
    }

    @PostMapping(path = "/reply/{commentId}")
    public String addLessonReplyComment(@PathVariable Integer commentId, HttpServletRequest request){
        String replyComment = request.getParameter("replyComment");
        int userId = (int) request.getSession().getAttribute("userId");
        int lastLessonId = (int) request.getSession().getAttribute("lastLessonId");

        commentService.addLessonReplyComment(userId, lastLessonId, replyComment, commentId);

        return "redirect:/student/lessonInfo/"+lastLessonId;
    }

    @GetMapping(path = "/showReplies/{commentId}")
    public String showCommentReplies(@PathVariable Integer commentId, HttpServletRequest request, Model model){
        int lastLessonId = (int) request.getSession().getAttribute("lastLessonId");
        Lesson lessonById = lessonService.getLessonById(lastLessonId);
        List<LessonComment> lessonComments = commentService.getLessonComments(lastLessonId);
        List<LessonComment> repliedComments = commentService.getRepliedComments(commentId, lastLessonId);


        model.addAttribute("lesson", lessonById);
        model.addAttribute("comments", lessonComments);
        model.addAttribute("repliedComments", repliedComments);
        model.addAttribute("isShowingReplies", true);
        model.addAttribute("repliedCommentId", commentId);
        return "/student/lessonInfo";
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
