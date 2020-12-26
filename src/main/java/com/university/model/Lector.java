package com.university.model;

import com.university.model.enums.Degree;
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
@Table(name = "lector")
public class Lector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    @Column
    private Degree degree;

    @OneToMany(mappedBy = "lector", fetch = FetchType.EAGER)
    private List<DepartmentLector> departmentLectors = new ArrayList<>();

    public Lector(long id, String name, Degree degree) {
        this.id = id;
        this.name = name;
        this.degree = degree;
    }
}
