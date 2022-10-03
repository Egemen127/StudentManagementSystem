package com.genspark.student.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.genspark.student.Entity.SystemUser;
import com.genspark.student.Entity.Ticket;
import com.genspark.student.Entity.Class;

public interface InstructorService extends StudentService{
    List<Ticket> getTickets();
    ResponseEntity<?> editUser(SystemUser user);
    ResponseEntity<?> getStudentsforClass(Class c);
    ResponseEntity<?> addGrade(int id, String className, String grade);
    ResponseEntity<?> removeGrade(int id, String className);
}
