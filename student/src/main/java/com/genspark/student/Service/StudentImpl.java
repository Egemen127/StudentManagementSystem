package com.genspark.student.Service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.genspark.student.Dao.TicketDao;
import com.genspark.student.Dao.UserDao;
import com.genspark.student.Entity.Ticket;

@Service
public class StudentImpl implements StudentService {
    @Autowired
    UserDao userRepo;
    @Autowired
    TicketDao ticketDao;

    @Override
    public ResponseEntity<?> getClasses(String name) {
        // RETURNS CLASSES FOR CURRENTLY LOGGED IN STUDENT
        try {
            return ResponseEntity.ok(userRepo.getUserByName(name).getClasses());
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("reason",e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> sendTicket(Ticket t) {
        //THE CONDITIONAL CHECKS IF THE TICKET IS ALREADY SENT
        if (ticketDao.findAll().stream()
        .filter(e -> (e.getSenderName().equals(t.getSenderName())&&
         e.getClassName().equals(t.getClassName())))
         .findFirst().isPresent()) {
            return ResponseEntity.badRequest().body("already sent a ticket for this class");
        }
        
        return ResponseEntity.ok(ticketDao.save(t));
    }
    @Override
    public ResponseEntity<?> getDetails(String username) {
        return ResponseEntity.ok(userRepo.getUserByName(username));
    }  
    
}
