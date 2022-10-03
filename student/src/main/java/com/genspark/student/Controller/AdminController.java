package com.genspark.student.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.genspark.student.Entity.SystemUser;
import com.genspark.student.Entity.Class;
import com.genspark.student.Service.AdminImpl;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminImpl adminService;

    @GetMapping("/classes")
    public ResponseEntity<?> getClass(Authentication auth) {
        System.out.println(auth.getName());
        return ResponseEntity.ok().body(adminService.getClasses(auth.getName()));
    }
    
    @GetMapping("/users")
    public ResponseEntity<?> getUsers(Authentication auth) {
        System.out.println(auth.getName());
        return ResponseEntity.ok().body(adminService.getUsers());
    }
    @GetMapping("/tickets")
    public ResponseEntity<?> getTickets() {
        return ResponseEntity.ok(adminService.getTickets());
    }
    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@RequestBody SystemUser u) {
        return adminService.signup(u);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editUser(@RequestBody SystemUser u,@PathVariable Integer id) {
        u.setId(id);
        return adminService.signup(u);
    }
    @PutMapping("/add/{id}")
    public ResponseEntity<?> addToClass(@PathVariable Integer id, @RequestParam("name") String className,Authentication auth) {
        System.out.println(auth.getAuthorities());
        System.out.println("sId: " + id + " Classname: "+ className);
        return adminService.addStudentToClass(id, className);
    }
    @PostMapping("/add")
    public ResponseEntity<?> addClass(@RequestBody Class c) {
        return ResponseEntity.ok(adminService.addClass(c));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Integer id) {
        return adminService.deleteUser(id);
    }
    @PostMapping("/addGrade/{id}")
    public ResponseEntity<?> addGrade(@PathVariable Integer id, @RequestParam("grade") String grade, @RequestParam("className") String className,Authentication auth ) {
        System.out.println(auth.getAuthorities());
        adminService.addGrade(id, className, grade);
        return ResponseEntity.ok("added");
    }
    
    @PutMapping("/remove/{id}")
    public ResponseEntity<?> removeFromClass(@PathVariable Integer id, @RequestParam("name") String className) {
        System.out.println("sId: " + id + " Classname: " + className);
        return adminService.removeStudentFromClass(id, className);
    }

    
    
}
