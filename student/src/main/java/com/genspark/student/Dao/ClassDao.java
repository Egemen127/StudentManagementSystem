package com.genspark.student.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.genspark.student.Entity.Class;

@Repository
public interface ClassDao extends JpaRepository<Class,String>{
    
}
