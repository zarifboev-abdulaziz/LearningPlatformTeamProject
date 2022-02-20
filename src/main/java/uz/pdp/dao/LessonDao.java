package uz.pdp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.pdp.model.Lesson;
import uz.pdp.model.Module;

import javax.persistence.Query;

@Component
public class LessonDao {
    @Autowired
    public SessionFactory sessionFactory;


    public void saveLesson(Lesson lesson) {
        Session currentSession = sessionFactory.getCurrentSession();
        if (lesson.getId() == null){
            currentSession.save(lesson);
        } else {
            currentSession.update(lesson);
        }
    }

    public Lesson getLessonById(Integer lessonId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Lesson lesson = currentSession.get(Lesson.class, lessonId);
        return lesson;
    }

    public void deleteLesson(Integer lessonId) {
        Session session = sessionFactory.getCurrentSession();
        Query query=session.createQuery("delete from lessons where id=" + lessonId);
        query.executeUpdate();
    }
}
