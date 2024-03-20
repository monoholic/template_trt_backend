package kr.co.trito.domain;

import jakarta.persistence.*;
import kr.co.trito.domain.response.WorkTimePK;

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
