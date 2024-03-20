package kr.co.trito.domain;

import jakarta.persistence.*;

import java.sql.Date;


@Entity
@Table(name = "TBSY_SAWON_INFO")
public class SawonInfo {
    @Id
    private String sawonNo;

    private String sawonGbn;

    private String sawonNm;

    private String deptCd;

    private String jikgyub;

    private String jikcheck;

    private String inDate;

    private String otDate;

    private String btDate;

    private String sexGb;

    private String hpTel;

    private String telNo;

    private String postNo;

    private String addr1;

    private String addr2;

    private String teamYn;

    private Date regDate;

    private Date uptDate;

    private String regId;

    private String uptId;
}
