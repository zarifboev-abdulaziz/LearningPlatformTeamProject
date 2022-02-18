package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.UserDao;
import uz.pdp.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;


    @Transactional
    public User registerUser(User registeringUser) {
        return userDao.registerUserToDb(registeringUser);
    }

    @Transactional
    public User loginUser(User loginUser) {
        return userDao.loginUser(loginUser);
    }

    @Transactional
    public List<User> getMentors() {
        return userDao.getMentors();
    }
}
