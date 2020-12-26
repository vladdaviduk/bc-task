package com.university.model.pk;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@Embeddable
public class DepartmentLectorPk implements Serializable {

    @Column
    private long departmentId;

    @Column
    private long lectorId;

    public DepartmentLectorPk(long departmentId, long lectorId) {
        this.departmentId = departmentId;
        this.lectorId = lectorId;
    }
}
