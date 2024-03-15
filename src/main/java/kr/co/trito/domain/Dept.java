package kr.co.trito.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
public class Dept {
    @Id
    @Column(name = "dept_cd")
    private String deptCd;

    @Column(name = "start_dt")
    private String startDt;

    @Column(name = "end_dt")
    private String endDt;

    @Column(name = "dept_nm")
    private String deptNm;

    @Column(name = "lev")
    private int lev;

    @Column(name = "up_dept_cd")
    private String upDeptCd;

    @Column(name = "dept_yn")
    private String deptYn;

    @Column(name = "sort_seq")
    private int sortSeq;

    @Column(name = "reg_dt")
    private Date regDt;

    @Column(name = "upt_dt")
    private Date uptDt;

    @Column(name = "yakeo_nm")
    private String yakeoNm;

    @Column(name = "reg_id")
    private String regId;

    @Column(name = "upt_id")
    private String uptId;
}
