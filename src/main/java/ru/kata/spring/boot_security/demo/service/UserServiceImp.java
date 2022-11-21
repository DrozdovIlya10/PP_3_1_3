package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService {
    private final UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void setIdForDelete(Long id) {
        userDao.setIdForDelete(id);
    }

    @Transactional
    @Override
    public void setUserForSave(User user) {
        userDao.setUserForSave(user);
    }

    @Override
    public List<User> getListUsers() {
        return userDao.getListUsers();
    }

    @Override
    public User getIdForUser(long id) {
        return userDao.getUserById(id);
    }

    @Transactional
    @Override
    public void setIdAndUserForEdit(long id, User user) {
        userDao.setIdAndUserForEdit(id, user);
    }

    @Override
    public User getUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.getUserByUsername(username);
    }
}
