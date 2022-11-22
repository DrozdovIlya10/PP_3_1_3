package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImp implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getListRoles() {
        List<Role> list = entityManager.createQuery("select role from Role role ").getResultList();
        return list;
    }

    @Override
    public void setRoleForSave(Role role) {
        entityManager.persist(role);
        entityManager.flush();
    }

    @Override
    public Role getRoleById(long id) {
        Role role = entityManager.find(Role.class, id);
        entityManager.detach(role);
        return role;
    }

    @Override
    public Set<Role> findByIdRoles(List<Long> roles) {
        TypedQuery<Role> q = entityManager.createQuery("select r from Role r where r.id in :role", Role.class);
        q.setParameter("role", roles);
        return new HashSet<>(q.getResultList());
    }
}
