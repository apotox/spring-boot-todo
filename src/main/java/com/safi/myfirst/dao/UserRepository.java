package com.safi.myfirst.dao;

import com.safi.myfirst.domaino.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long>
{
    public User findByUsername(String username);
}
