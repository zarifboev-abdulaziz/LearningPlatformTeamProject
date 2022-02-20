package uz.pdp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import uz.pdp.model.Course;
import uz.pdp.model.Module;

import javax.persistence.Query;

@Component
public class ModuleDao {
    @Autowired
    public SessionFactory sessionFactory;

    public void saveModule(Module module) {
        Session currentSession = sessionFactory.getCurrentSession();
        if (module.getId() == null){
            currentSession.save(module);
        } else {
            currentSession.update(module);
        }
    }

    public Module getModuleById(Integer moduleId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Module module = currentSession.get(Module.class, moduleId);

        return module;
    }

    public void deleteModuleById(Integer moduleId) {
        Session session = sessionFactory.getCurrentSession();
        Query query=session.createQuery("delete from modules where id=" + moduleId);
        query.executeUpdate();
    }
}
