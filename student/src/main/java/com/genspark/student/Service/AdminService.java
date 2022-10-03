package com.genspark.student.Service;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.genspark.student.Entity.SystemUser;

import com.genspark.student.Entity.Class;

public interface AdminService extends InstructorService{
    ResponseEntity<?> signup(SystemUser user);
    ResponseEntity<?> addStudentToClass(int userid, String className);
    ResponseEntity<?> editClass(Class c);
    ResponseEntity<?> deleteUser(int userId);
    ResponseEntity<?> addClass(Class c);
    ResponseEntity<?> removeStudentFromClass(int userid, String className);
    List<SystemUser> getUsers();
}
