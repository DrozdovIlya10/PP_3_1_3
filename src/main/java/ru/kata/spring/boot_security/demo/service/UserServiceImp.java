package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
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

    @Transactional(readOnly = true)
    @Override
    public List<User> getListUsers() {
        return userDao.getListUsers();
    }

    @Transactional(readOnly = true)
    @Override
    public User getIdForUser(long id) {
        return userDao.getUserById(id);
    }

    @Transactional
    @Override
    public void setUserForEdit(User user) {
        userDao.setUserForEdit(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.getUserByUsername(username);
    }
}
