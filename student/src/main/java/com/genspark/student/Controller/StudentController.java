package com.genspark.student.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genspark.student.Entity.Ticket;
import com.genspark.student.Service.StudentImpl;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StudentController {
    @Autowired
    StudentImpl studentService;


    @GetMapping("/")
    public ResponseEntity<?> getClass(Authentication auth) {
        System.out.println(auth.getDetails());
        return ResponseEntity.ok().body(studentService.getClasses(auth.getName()));
    }

    @PostMapping("/sendTicket")
    public ResponseEntity<?> sendTicket(@RequestBody Ticket t,Authentication auth) {
        t.setSenderName(auth.getName());
        return studentService.sendTicket(t);
    }
}
