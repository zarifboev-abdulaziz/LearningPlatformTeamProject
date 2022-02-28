package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.CommentDao;
import uz.pdp.helper.LessonComment;
import uz.pdp.helper.TaskComment;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentDao commentDao;

    @Transactional
    public void addLessonComment(int userId, Integer lessonId, String comment) {
        commentDao.addComment(userId, lessonId, comment);
    }

    @Transactional
    public List<LessonComment> getLessonComments(Integer lessonId) {
        return commentDao.getLessonComments(lessonId);
    }

    public void addTaskComment(int userId, Integer taskId, String comment) {
        commentDao.addTaskComment(userId, taskId, comment);
    }

    public List<TaskComment> getTaskComments(Integer taskId) {
        return commentDao.getTaskComments(taskId);
    }
}
