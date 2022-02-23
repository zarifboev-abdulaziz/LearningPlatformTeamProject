package uz.pdp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.pdp.model.Option;
import uz.pdp.model.Task;

import javax.persistence.Query;

@Component
public class OptionDao {
    @Autowired
    public SessionFactory sessionFactory;


    public void saveOption(Option option) {
        Session currentSession = sessionFactory.getCurrentSession();
        if (option.getId() == null){
            currentSession.save(option);
        } else {
            currentSession.update(option);
        }
    }

    public Option getOptionById(Integer optionId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Option option = currentSession.get(Option.class, optionId);
        return option;
    }

    public void deleteOption(Integer optionId) {
        Session session = sessionFactory.getCurrentSession();
        Query query=session.createQuery("delete from options where id=" + optionId);
        query.executeUpdate();
    }
}
