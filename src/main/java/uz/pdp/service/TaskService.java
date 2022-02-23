package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.TaskDao;
import uz.pdp.model.Task;

import javax.transaction.Transactional;

@Service
public class TaskService {
    @Autowired
    TaskDao taskDao;


    @Transactional
    public void saveTask(Task task) {
        taskDao.saveTask(task);
    }

    @Transactional
    public Task getTaskById(Integer taskId) {
        return taskDao.getTaskById(taskId);
    }

    @Transactional
    public void deleteTask(Integer taskId) {
        taskDao.deleteTask(taskId);
    }
}
