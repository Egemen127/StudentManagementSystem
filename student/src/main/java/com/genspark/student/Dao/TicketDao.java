package com.genspark.student.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.genspark.student.Entity.Ticket;

@Repository
public interface TicketDao extends JpaRepository<Ticket,Integer>{
    
}
