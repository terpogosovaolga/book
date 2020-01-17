package Dao;

import models.Role;

public interface IRoleDao {
    Role getRoleById(long id); //returns a role with this id SELECT

}
