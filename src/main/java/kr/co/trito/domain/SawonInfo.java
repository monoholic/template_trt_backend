package kr.co.trito.domain;

import jakarta.persistence.*;

import java.sql.Date;


@Entity
@Table(name = "SAWON_INFO")
public class SawonInfo {
    @Id
    @Column(name = "sawon_no")
    private String sawonNo;

    @Column(name = "sawon_gbn")
    private String sawonGbn;

    @Column(name = "sawon_nm")
    private String sawonNm;

    @Column(name = "dept_cd")
    private String deptCd;

    private String jikgyub;

    private String jikcheck;

    @Column(name = "in_date")
    private String inDate;

    @Column(name = "ot_date")
    private String otDate;

    @Column(name = "bt_date")
    private String btDate;

    @Column(name = "sex_gb")
    private String sexGb;

    @Column(name = "hp_tel")
    private String hpTel;

    @Column(name = "tel_no")
    private String telNo;

    @Column(name = "post_no")
    private String postNo;

    private String addr1;

    private String addr2;

    @Column(name = "team_yn")
    private String teamYn;

    @Column(name = "reg_date")
    private Date regDate;

    @Column(name = "upt_date")
    private Date uptDate;

    @Column(name = "reg_id")
    private String regId;

    @Column(name = "upt_id")
    private String uptId;
}
