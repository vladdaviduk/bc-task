package com.university.model;

import com.university.model.pk.DepartmentLectorPk;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "department_lector")
public class DepartmentLector {

    @EmbeddedId
    private DepartmentLectorPk id;

    @ManyToOne
    @MapsId("departmentId")
    @JoinColumn(name = "department.id")
    private Department department;

    @ManyToOne
    @MapsId("lectorId")
    @JoinColumn(name = "lector.id")
    private Lector lector;

    @Column
    private double salary;

}
