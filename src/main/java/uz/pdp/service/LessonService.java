package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.LessonDao;
import uz.pdp.model.Lesson;

import javax.transaction.Transactional;

@Service
public class LessonService {

    @Autowired
    LessonDao lessonDao;

    @Transactional
    public void saveLesson(Lesson lesson) {
        lessonDao.saveLesson(lesson);
    }

    @Transactional
    public Lesson getLessonById(Integer lessonId) {
        return lessonDao.getLessonById(lessonId);
    }

    @Transactional
    public void deleteLesson(Integer lessonId) {
        lessonDao.deleteLesson(lessonId);
    }
}
