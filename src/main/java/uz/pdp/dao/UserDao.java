package uz.pdp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.model.Course;
import uz.pdp.model.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao {
    @Autowired
    public SessionFactory sessionFactory;


    public User registerUserToDb(User registeringUser) {
        Session currentSession = sessionFactory.getCurrentSession();
        Serializable save = currentSession.save(registeringUser);

        User user = currentSession.get(User.class, save);
        return user;
    }

    public User loginUser(User loginUser) {
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root< User > root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        javax.persistence.Query query = session.createQuery(criteriaQuery);
        List<User> userList = query.getResultList();

        for (User user : userList) {
            if (user.getEmail().equals(loginUser.getEmail()) && user.getPassword().equals(loginUser.getPassword())){
                return user;
            }
        }

        return new User();
    }

    public List<User> getMentors() {
        List<User> mentorList = new ArrayList<>();
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root< User > root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        javax.persistence.Query query = session.createQuery(criteriaQuery);

        List<User> userList = (List<User>) query.getResultList();

        for (User user : userList) {
            if (user.getRoleId() == 2){
                mentorList.add(user);
            }
        }
        return mentorList;
    }

    public User getUserById(String userId) {
        Session currentSession = sessionFactory.getCurrentSession();
        User user = currentSession.get(User.class, Integer.parseInt(userId));
        return user;
    }

    public List<User> getAllUser(){
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root< User > root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        javax.persistence.Query query = session.createQuery(criteriaQuery);

        List<User> userList = (List<User>) query.getResultList();
        return userList;
    }

    public User editUser(User user){
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(user);
        User userById = getUserById(String.valueOf(user.getId()));
        return userById;
    }
}
