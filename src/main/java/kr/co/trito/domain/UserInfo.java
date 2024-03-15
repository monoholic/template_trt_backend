package kr.co.trito.domain;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table( name = "USER_INFO" )
public class UserInfo {
    @Id
    @Column(name = "user_id")
    private String userId;

    private String passwd;

    @Column(name = "user_nm")
    private String userNm;

    @Column(name = "sawon_no")
    private String sawonNo;

    @Column(name = "use_yn")
    private String useYn;

    @Column(name = "reg_date")
    private Date regDate;

    @Column(name = "upt_date")
    private Date uptDate;

    @Column(name = "reg_id")
    private String regId;

    @Column(name = "upt_id")
    private String uptId;
}