package com.genspark.student.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.genspark.student.Entity.SystemUser;

@Repository
public interface UserDao extends JpaRepository<SystemUser,Integer> {
    @Query("SELECT User from SystemUser User WHERE User.name=:name")
    public SystemUser getUserByName(@Param("name") String name); 
}
