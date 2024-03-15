package kr.co.trito.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "USER_INFO")
public class UserInfo {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private String user_id;

    private String passwd;

    private String user_nm;

    private String sawon_no;

    private String use_yn;

    private LocalDateTime reg_date;

    private LocalDateTime upt_date;

    private String reg_id;

    private String upt_id;
}