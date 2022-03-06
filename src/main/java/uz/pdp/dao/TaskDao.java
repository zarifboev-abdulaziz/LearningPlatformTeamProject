package uz.pdp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.helper.CompletedTasks;
import uz.pdp.model.Lesson;
import uz.pdp.model.Option;
import uz.pdp.model.Task;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TaskDao {
    @Autowired
    public SessionFactory sessionFactory;
    @Autowired
    JdbcTemplate template;

    public Task saveTask(Task task) {
        Session currentSession = sessionFactory.getCurrentSession();
        if (task.getId() == null){
            Serializable save = currentSession.save(task);
            return currentSession.get(Task.class, save);
        } else {
            currentSession.update(task);
            return currentSession.get(Task.class, task.getId());
        }
    }


    public Task getTaskById(Integer taskId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Task task = currentSession.get(Task.class, taskId);

        Map<Integer, Option> optionMap = new HashMap<>();
        for (Option option : task.getOptions()) {
            optionMap.put(option.getId(), option);
        }
        task.getOptions().removeAll(task.getOptions());
        for (Option value : optionMap.values()) {
            task.getOptions().add(value);
        }

        return task;
    }

    public void deleteTask(Integer taskId) {
        Session session = sessionFactory.getCurrentSession();
        Query query=session.createQuery("delete from tasks where id=" + taskId);
        query.executeUpdate();
    }

    public void addTaskToCompletedTasks(int taskId, Integer userId) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "insert into user_completed_tasks(user_id, task_id) VALUES ("+userId+", "+taskId+")";
        NativeQuery nativeQuery = session.createNativeQuery(sql);
        nativeQuery.executeUpdate();
    }

    public List<CompletedTasks> getUserCompletedTasks(Integer userId) {
        String sql = "select * from user_completed_tasks where user_id = " + userId;

        List<CompletedTasks> completedTasksList = template.query(sql, (rs, rowNum) -> {
            CompletedTasks completedTasks = new CompletedTasks();
            completedTasks.setUserId(rs.getInt("user_id"));
            completedTasks.setTaskId(rs.getInt("task_id"));
            return completedTasks;
        });

        return completedTasksList;
    }
}
