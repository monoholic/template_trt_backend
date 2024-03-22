package kr.co.trito.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

import java.sql.Date;

@Entity
@IdClass(WorkTimePK.class)
@Table(name = "TBPY_WORKTIME")
public class WorkTime {
    @Id
    private String sawonNo;

    @Id
    private Date startDt;

    private Date endDt;

    private String gubunCd;

    private String remarks;

    private String acceptIp;

    private String cause;
}
