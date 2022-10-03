package com.genspark.student.Entity;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SystemUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String password;
    @ManyToMany(mappedBy = "students", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JsonIgnoreProperties({ "students" })
    private Set<Class> classes = new HashSet<>();
    @ElementCollection
    @CollectionTable(name = "grade_class_mapping", joinColumns = {
            @JoinColumn(name = "id")})
    @MapKeyColumn(name = "className")
    @Column(name = "grade")
    //value of this map can be turned into an array that includes midterm and final results
    private Map<String, String> grades;
    private UserRole role;

}
