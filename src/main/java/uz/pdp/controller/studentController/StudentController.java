package uz.pdp.controller.studentController;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.helper.CompletedTasks;
import uz.pdp.helper.LessonComment;
import uz.pdp.helper.Result;
import uz.pdp.helper.TaskComment;
import uz.pdp.model.*;
import uz.pdp.service.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    CourseService courseService;
    @Autowired
    ModuleService moduleService;
    @Autowired
    LessonService lessonService;
    @Autowired
    TaskService taskService;
    @Autowired
    OptionService optionService;
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;



    @GetMapping(path = "/home")
    public String studentHome(HttpServletRequest request, Model model) throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute("roleId", 1);
        List<Course> allCourses = courseService.getAllCourses(1, 1);

        model.addAttribute("allCourses", allCourses);
        return "/student/home";
    }

    @GetMapping(path = "/activeCourses/{page}")
    public String showActiveCourses(@PathVariable Integer page, HttpServletRequest request, Model model) throws IOException {
        HttpSession session = request.getSession();
        int roleId = (int) session.getAttribute("roleId");
        List<Course> allCourses = courseService.getAllCourses(page, roleId);
        Integer pages = courseService.getTotalPages(roleId);

        model.addAttribute("courseList", allCourses);
        model.addAttribute("pages", pages);
        return "/student/activeCourses";
    }


    @GetMapping(path = "/courseInfo/{courseId}")
    public String showCoursesInfo(@PathVariable Integer courseId, HttpServletRequest request, Model model) throws IOException {
        Course courseById = courseService.getCourseById(courseId);
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");

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

        boolean isCoursePurchased = false;

        List<Course> userPurchasedCourses = courseService.getUserPurchasedCourses(userId);
        for (Course userPurchasedCours : userPurchasedCourses) {
            if (userPurchasedCours.getId() == courseId){
                isCoursePurchased = true;
            }
        }

        model.addAttribute("course", courseById);
        model.addAttribute("lessonCount", lessonCount);
        model.addAttribute("mentors", mentors);
        model.addAttribute("isCoursePurchased", isCoursePurchased);
        return "/student/courseInfo";
    }


    @GetMapping(path = "/purchaseCourse/{courseId}")
    public String purchaseCourse(@PathVariable Integer courseId, HttpServletRequest request, Model model) throws IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");

        courseService.purchaseCourse(userId, courseId);

        return "redirect:/student/courseInfo/" + courseId;
    }


    @GetMapping(path = "/myCourses")
    public String studentCourses(HttpServletRequest request, Model model) throws IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");

        List<Course> myCourses = courseService.getUserPurchasedCourses(userId);

        List<Integer> progressBarForEachCourse = courseService.getProgressBarForEachCourse(userId, myCourses);

        model.addAttribute("myCourses", myCourses);
        model.addAttribute("progressBar", progressBarForEachCourse);
        return "/student/myCourses";
    }

    //    modules
    @GetMapping(path = "/modules/{courseId}")
    public String showCourseModules(@PathVariable Integer courseId, HttpServletRequest request, Model model) throws IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        session.setAttribute("lastCourseId", courseId);
        Course courseById = courseService.getCourseById(courseId);
        boolean isCourseCompleted = courseService.isCourseCompleted(courseId, userId);

        List<User> mentors = new ArrayList<>();
        for (User user : courseById.getUsers()) {
            for (Role role : user.getRoles()) {
                if (role.getId() == 2){
                    mentors.add(user);
                }
            }
        }
        List<Integer> progressBarForEachModule = courseService.getProgressBarForEachModule(userId, courseById.getModules());


        model.addAttribute("course", courseById);
        model.addAttribute("isCourseCompleted", isCourseCompleted);
        model.addAttribute("mentors", mentors);
        model.addAttribute("progressBar", progressBarForEachModule);
        return "/student/modules";
    }

    @GetMapping("/getCertificate/{courseId}")
    public String getCertificate(@PathVariable Integer courseId,  Model model, HttpServletRequest request){
        int userId = (int) request.getSession().getAttribute("userId");
        Course courseById = courseService.getCourseById(courseId);
        User userById = userService.getUserById(String.valueOf(userId));

        model.addAttribute("user", userById);
        model.addAttribute("course", courseById);
        return "/student/certificate";
    }

//    lessons
    @GetMapping(path = "/lessons/{moduleId}")
    public String showModuleLessons(@PathVariable Integer moduleId, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("lastModuleId", moduleId);
        int userId = (int) session.getAttribute("userId");
        Module moduleById = moduleService.getModuleById(moduleId);
        List<Lesson> lessons = moduleById.getLessons();
        List<Integer> progressBarForEachLesson = courseService.getProgressBarForEachLesson(userId, lessons);

        model.addAttribute("lessons", lessons);
        model.addAttribute("module", moduleById);
        model.addAttribute("progressBar", progressBarForEachLesson);
        return "/student/lessons";
    }


    @GetMapping(path = "/lessonInfo/{lessonId}")
    public String showLesson(@PathVariable Integer lessonId, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("lastLessonId", lessonId);
        Lesson lessonById = lessonService.getLessonById(lessonId);
        List<LessonComment> lessonComments = commentService.getLessonComments(lessonId);

        model.addAttribute("lesson", lessonById);
        model.addAttribute("comments", lessonComments);
        return "/student/lessonInfo";
    }

    // value = /student/lessonInfo/reply/{commentId}
    @GetMapping(path = "/lessonInfo/reply/{commentId}")
    public String replyToComment(@PathVariable Integer commentId, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("lastCommentId", commentId);
        int lessonId = (int) session.getAttribute("lastLessonId");
        Lesson lessonById = lessonService.getLessonById(lessonId);
        List<LessonComment> lessonComments = commentService.getLessonComments(lessonId);

        model.addAttribute("lesson", lessonById);
        model.addAttribute("comments", lessonComments);
        model.addAttribute("replyingCommentId", commentId);
        return "/student/lessonInfo";
    }


    @GetMapping(path = "/tasks/{lessonId}/{taskId}")
    public String showLessonTasks(@PathVariable Integer lessonId, @PathVariable Integer taskId, Model model, HttpServletRequest request){
        Integer userId = (int)request.getSession().getAttribute("userId");
        Lesson lessonById = lessonService.getLessonById(lessonId);
        Task taskById = taskService.getTaskById(taskId);
        List<String> bgColor = checkBgColor(lessonById, userId);
        List<TaskComment> taskComments = commentService.getTaskComments(taskId);

        model.addAttribute("lesson", lessonById);
        model.addAttribute("task", taskById);
        model.addAttribute("bgColor", bgColor);
        model.addAttribute("comments", taskComments);
        return "/student/tasks";
    }


    @PostMapping(path = "/checkOption/{lessonId}/{taskId}")
    public String checkUserOption(@PathVariable Integer lessonId, @PathVariable Integer taskId, Model model, HttpServletRequest request){
        String option = request.getParameter("option");
        Integer userId = (int)request.getSession().getAttribute("userId");
        Lesson lessonById = lessonService.getLessonById(lessonId);
        Task taskById = taskService.getTaskById(taskId);

        Result result = checkOption(option, userId);
        List<String> bgColor = checkBgColor(lessonById, userId);

        model.addAttribute("lesson", lessonById);
        model.addAttribute("task", taskById);
        model.addAttribute("result", result);
        model.addAttribute("bgColor", bgColor);
        return "/student/tasks";

    }

    private List<String> checkBgColor(Lesson lessonById, Integer userId) {
        List<String> result = new ArrayList<>();
        List<CompletedTasks> completedTasksList = taskService.getUserCompletedTasks(userId);

        for (Task task : lessonById.getTasks()) {
            if (completedTasksList.stream().anyMatch(completedTasks -> completedTasks.getTaskId() == task.getId())){
                result.add("bg-success");
            } else {
                result.add("bg-danger");
            }
        }

        return result;
    }

    private Result checkOption(String option, Integer userId) {
        if (option == null){
            return new Result(false, "Please Select one option", null);
        }

        int optionId = Integer.parseInt(option);
        Option optionById = optionService.getOptionById(optionId);



        if (optionById.isRightAnswer()){
            List<CompletedTasks> userCompletedTasks = taskService.getUserCompletedTasks(userId);
            for (CompletedTasks userCompletedTask : userCompletedTasks) {
                if (userCompletedTask.getTaskId() == optionById.getTask().getId() && userCompletedTask.getUserId() == userId){
                    return new Result(true, "You selected true option", optionById);
                }
            }

            taskService.addTaskToCompletedTasks(optionById.getTask().getId(), userId);
            return new Result(true, "You selected true option", optionById);
        } else {
            return new Result(false, "You selected wrong option, please try again", optionById);
        }
    }

}
