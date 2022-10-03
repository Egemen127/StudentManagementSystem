package com.genspark.student.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.genspark.student.Dao.ClassDao;
import com.genspark.student.Dao.TicketDao;
import com.genspark.student.Dao.UserDao;
import com.genspark.student.Entity.Class;
import com.genspark.student.Entity.SystemUser;
import com.genspark.student.Entity.Ticket;
import com.genspark.student.Entity.UserRole;

@Service
public class InstructorImpl implements InstructorService{
    @Autowired
    TicketDao ticketDao;
    @Autowired
    UserDao userRepo;
    @Autowired
    ClassDao classDao;

    @Override
    public ResponseEntity<?> getClasses(String name) {
        SystemUser current = userRepo.getUserByName(name);
        try {
            return ResponseEntity.ok(classDao.findAll().stream().filter(e-> e.getInstructor().getId() == current.getId()).collect(Collectors.toSet()));

        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.badRequest().body(Map.of("reason", e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> sendTicket(Ticket t) {
        return ResponseEntity.ok(ticketDao.save(t));
    }


    @Override
    public List<Ticket> getTickets() {
        // TODO Auto-generated method stub
        return ticketDao.findAll();
    }

    @Override
    public ResponseEntity<?> editUser(SystemUser user) {
        // TODO Auto-generated method stub
        if(userRepo.findById(user.getId()).isEmpty())
        return ResponseEntity.badRequest().body("User not found");

        return ResponseEntity.ok(userRepo.save(user));
    }

    @Override
    public ResponseEntity<?> getStudentsforClass(Class c) {
        // TODO Auto-generated method stub
        Set<SystemUser> students = userRepo.findAll().stream().filter(e->e.getClasses().contains(c)).collect(Collectors.toSet());


        return ResponseEntity.ok(students);
    }

    @Override
    public ResponseEntity<?> addGrade(int id, String className, String grade) {
        // TODO Auto-generated method stub
        SystemUser s = userRepo.getReferenceById(id);
        s.getGrades().put(className, grade);
        if (s.getRole() == UserRole.STUDENT) {
            userRepo.save(s);
            return ResponseEntity.ok(s.getGrades());
        } else {
            return ResponseEntity.badRequest().body("only students can have grades");
        }
    }

    @Override
    public ResponseEntity<?> removeGrade(int id, String className) {
        // TODO Auto-generated method stub
        SystemUser s = userRepo.getReferenceById(id);
        s.getGrades().remove(className);
        if (s.getRole() == UserRole.STUDENT) {
            userRepo.save(s);
            return ResponseEntity.ok(s.getGrades());
        } else {
            return ResponseEntity.badRequest().body("only students can have grades");
        }
    }
    
    @Override
    public ResponseEntity<?> getDetails(String username) {
        return ResponseEntity.ok(userRepo.getUserByName(username));
    }
    


}
