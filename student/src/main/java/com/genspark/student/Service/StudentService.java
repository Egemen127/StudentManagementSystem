package com.genspark.student.Service;

import org.springframework.http.ResponseEntity;
import com.genspark.student.Entity.Ticket;


public interface StudentService {
    

    ResponseEntity<?> getClasses(String name);
    ResponseEntity<?> sendTicket(Ticket t); 
    ResponseEntity<?> getDetails(String username);
}
