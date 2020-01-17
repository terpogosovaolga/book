package Dao;

import Hibernate.HibernateSessionFactory;
import models.Role;

public class RoleDao implements IRoleDao {
    @Override
    public Role getRoleById(long id) {
        return  HibernateSessionFactory.getSessionFactory().openSession().get(Role.class, id);
    }
}
