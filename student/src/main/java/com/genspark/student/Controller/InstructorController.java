package com.genspark.student.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.genspark.student.Entity.Ticket;
import com.genspark.student.Service.InstructorImpl;

@RestController
@RequestMapping("/instructor")
public class InstructorController {
    @Autowired
    InstructorImpl instructorService;
    
    @GetMapping("/students")
    public ResponseEntity<?> getStudents(Authentication auth) {
        return instructorService.getClasses(auth.getName());
    }
    
    @PostMapping("/addGrade/{id}")
    public ResponseEntity<?> addGrade(@PathVariable Integer id, @RequestParam("grade") String grade,
            @RequestParam("className") String className) {
        instructorService.addGrade(id, className, grade);
        return ResponseEntity.ok("added");
    }
    @GetMapping("/tickets")
    public ResponseEntity<?> getTickets() {
        return ResponseEntity.ok(instructorService.getTickets());
    }
    @PostMapping("/responseTicket")
    public ResponseEntity<?> responseTicket(@RequestBody Ticket t) {
        return instructorService.sendTicket(t);
    } 
}
