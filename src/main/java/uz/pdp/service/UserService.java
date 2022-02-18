package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.UserDao;
import uz.pdp.model.User;

@Service
public class UserService {
    @Autowired
    UserDao userDao;


    public User registerUser(User registeringUser) {
        return userDao.registerUserToDb(registeringUser);
    }

    public User loginUser(User loginUser) {
        return userDao.loginUser(loginUser);
    }
}
