package uz.pdp.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.helper.LessonComment;
import uz.pdp.helper.TaskComment;
import uz.pdp.model.User;
import uz.pdp.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentDao {
    @Autowired
    public SessionFactory sessionFactory;
    @Autowired
    JdbcTemplate template;
    @Autowired
    UserService userService;


    public void addComment(int userId, Integer lessonId, String comment) {
        String sql = "INSERT INTO lesson_comments (user_id, lesson_id, body) VALUES ("+userId+", "+lessonId+", '"+comment+"')";
        template.execute(sql);
    }

    public List<LessonComment> getLessonComments(Integer lessonId) {
        String query = "select * from lesson_comments where lesson_id = " + lessonId;

        List<LessonComment> lessonComments = template.query(query, (rs, rowNum) -> {
           LessonComment lessonComment = new LessonComment();
           lessonComment.setId(rs.getInt("id"));
            User user_id = userService.getUserById(String.valueOf(rs.getInt("user_id")));
            lessonComment.setUser(user_id);
            lessonComment.setBody(rs.getString("body"));

            return lessonComment;
        });


        return lessonComments;
    }

    public void addTaskComment(int userId, Integer taskId, String comment) {
        String sql = "INSERT INTO task_comments (user_id, task_id, body) VALUES ("+userId+", "+taskId+", '"+comment+"')";
        template.execute(sql);
    }

    public List<TaskComment> getTaskComments(Integer taskId) {
        String query = "select * from task_comments where task_id = " + taskId;

        List<TaskComment> taskComments = template.query(query, (rs, rowNum) -> {
           TaskComment taskComment = new TaskComment();
           taskComment.setId(rs.getInt("id"));
            User user_id = userService.getUserById(String.valueOf(rs.getInt("user_id")));
            taskComment.setUser(user_id);
            taskComment.setBody(rs.getString("body"));

            return taskComment;
        });

        return taskComments;
    }
}
