package com.genspark.student.Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Class {
    @Id
    private String className;
    @OneToOne
    @JsonIgnoreProperties({ "classes" })
    private SystemUser instructor;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name="classList",
        joinColumns= {@JoinColumn(name="className")} ,
        inverseJoinColumns= {@JoinColumn(name = "id")}
            )
    @JsonIgnoreProperties({ "classes" })
    private Set<SystemUser> students = new HashSet<>();
}
