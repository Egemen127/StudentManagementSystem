package com.genspark.student.Service;

import java.util.List;

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
public class AdminImpl implements AdminService {

    @Autowired
    private TicketDao ticketDao;
    @Autowired
    private UserDao userRepo;
    @Autowired
    private ClassDao classDao;


    @Override
    public List<Ticket> getTickets() {
        // TODO Auto-generated method stub
        return ticketDao.findAll();
    }

    @Override
    public ResponseEntity<?> editUser(SystemUser user) {
        // TODO Auto-generated method stub
        if (userRepo.findById(user.getId()).isEmpty())
            return ResponseEntity.badRequest().body("User not found");

        return ResponseEntity.ok(userRepo.save(user));
    }

    @Override
    public ResponseEntity<?> getClasses(String name) {
        // TODO Auto-generated method stub
        return ResponseEntity.ok(classDao.findAll());
    }

    @Override
    public ResponseEntity<?> sendTicket(Ticket t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<?> signup(SystemUser user) {
        // TODO Auto-generated method stub
        return ResponseEntity.ok(userRepo.save(user));
    }

    @Override
    public ResponseEntity<?> addStudentToClass(int userid, String className) {
       
        Class c = classDao.getReferenceById(className);
        SystemUser u = userRepo.getReferenceById(userid);
        if(u.getRole().equals(UserRole.STUDENT)){
        u.getClasses().add(c);
        c.getStudents().add(u);
        classDao.save(c);
        return ResponseEntity.ok("added");
        } else {
            return ResponseEntity.badRequest().body("only students can be added to the class");
        }
    }
    @Override
    public ResponseEntity<?> editClass(Class c) {
        return ResponseEntity.ok(classDao.save(c));
    }
    @Override
    public List<SystemUser> getUsers() {
        // TODO Auto-generated method stub
        return userRepo.findAll();
    }

    @Override
    public ResponseEntity<?> deleteUser(int userId) {
        // TODO Auto-generated method stub
        SystemUser u = userRepo.findById(userId).orElseThrow();
        userRepo.delete(u);
        return ResponseEntity.ok().body("deleted");
    }

    @Override
    public ResponseEntity<?> getStudentsforClass(Class c) {
        // TODO Auto-generated method stub
        List<SystemUser> students = userRepo.findAll();

        return ResponseEntity.ok(students);
    }

    @Override
    public ResponseEntity<?> addClass(Class c) {
        // TODO Auto-generated method stub
        return ResponseEntity.ok(classDao.save(c));
    }
    
    @Override
    public ResponseEntity<?> addGrade(int id, String className, String grade) {
        // TODO Auto-generated method stub
        SystemUser s = userRepo.getReferenceById(id);
        s.getGrades().put(className, grade);
        if(s.getRole().equals(UserRole.STUDENT)){
        userRepo.save(s);
        return ResponseEntity.ok("added");
        } else {
            return ResponseEntity.badRequest().body("only students can have grades");
        }  
    }

    @Override
    public ResponseEntity<?> removeStudentFromClass(int userid, String className) {
        // TODO Auto-generated method stub
        Class c = classDao.getReferenceById(className);
        SystemUser u = userRepo.getReferenceById(userid);
        u.getClasses().remove(c);
        c.getStudents().remove(u);
        classDao.save(c);
        return ResponseEntity.ok("removed");
    }
    
    @Override
    public ResponseEntity<?> removeGrade(int id, String className) {
        // TODO Auto-generated method stub
        return null;
    }   
    
    @Override
    public ResponseEntity<?> getDetails(String username) {
        return ResponseEntity.ok(userRepo.getUserByName(username));
    }
}
