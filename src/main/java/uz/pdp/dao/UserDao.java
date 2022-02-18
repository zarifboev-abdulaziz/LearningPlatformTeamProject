package uz.pdp.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import uz.pdp.helper.Helper;
import uz.pdp.model.User;

import java.io.Serializable;
import java.util.List;

@Component
public class UserDao {


    public User registerUserToDb(User registeringUser) {
        Session session = Helper.getSession();
        Transaction transaction = session.beginTransaction();

        Serializable save = session.save(registeringUser);
        User user = session.get(User.class, save);
        transaction.commit();
        return user;
    }

    public User loginUser(User loginUser) {
        Session session = Helper.getSession();
        Transaction transaction = session.beginTransaction();

        User returningUser = new User();

        Query from_users = session.createQuery("from users");
        List<User> userList = from_users.list();

        for (User user : userList) {
            if (user.getEmail().equals(loginUser.getEmail()) && user.getPassword().equals(loginUser.getPassword())){
                returningUser = user;
                break;
            }
        }

        transaction.commit();
        return returningUser;
    }
}
