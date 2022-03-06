package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.TaskDao;
import uz.pdp.helper.CompletedTasks;
import uz.pdp.model.Task;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskDao taskDao;


    @Transactional
    public Task saveTask(Task task) {
        return taskDao.saveTask(task);
    }

    @Transactional
    public Task getTaskById(Integer taskId) {
        return taskDao.getTaskById(taskId);
    }

    @Transactional
    public void deleteTask(Integer taskId) {
        taskDao.deleteTask(taskId);
    }

    @Transactional
    public void addTaskToCompletedTasks(int taskId, Integer userId) {
        taskDao.addTaskToCompletedTasks(taskId, userId);
    }

    @Transactional
    public List<CompletedTasks> getUserCompletedTasks(Integer userId) {
        return taskDao.getUserCompletedTasks(userId);
    }
}
