package kr.co.trito.domain;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table( name = "TBSY_USER_INFO" )
public class UserInfo {
    @Id
    private String userId;

    private String passwd;

    private String userNm;

    private String sawonNo;

    private String useYn;

    private Date regDate;

    private Date uptDate;

    private String regId;

    private String uptId;
}