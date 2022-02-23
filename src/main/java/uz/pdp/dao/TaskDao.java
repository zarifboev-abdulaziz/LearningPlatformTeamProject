package uz.pdp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.pdp.model.Lesson;
import uz.pdp.model.Option;
import uz.pdp.model.Task;

import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;

@Component
public class TaskDao {
    @Autowired
    public SessionFactory sessionFactory;

    public void saveTask(Task task) {
        Session currentSession = sessionFactory.getCurrentSession();
        if (task.getId() == null){
            currentSession.save(task);
        } else {
            currentSession.update(task);
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
}
