package ru.kata.spring.boot_security.demo.dao;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {


    final BCryptPasswordEncoder bCryptPasswordEncoder;
    @PersistenceContext
    private EntityManager entityManager;

    public UserDaoImp(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void setUserForSave(User user) {
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    public void setIdForDelete(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getListUsers() {
        List<User> list = entityManager.createQuery("select user from User user ").getResultList();
        return list;
    }

    @Override
    public User getUserById(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.detach(user);
        return user;
    }

    @Override
    public void setUserForEdit(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUserByUsername(String username) {
        List<User> list = getListUsers();
        User user = list.stream().filter(user1 -> username.equals(user1.getUsername())).findAny().orElse(null);
        return user;
    }
}

