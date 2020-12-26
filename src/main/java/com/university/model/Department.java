package com.university.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String head;


    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    private List<DepartmentLector> departmentLectors = new ArrayList<>();

    public Department(long id, String name, String head) {
        this.id = id;
        this.name = name;
        this.head = head;
    }
}
