package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService {
    private UserDao userDao;
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional(readOnly = false)
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public List<User> getListUsers() {
        return userDao.getListUser();
    }

    @Override
    public User getOwner(String model, int series) {
        return userDao.getOwner(model, series);
    }

}
