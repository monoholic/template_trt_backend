package kr.co.trito.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "TBSY_CODE")
public class Code {
    @Id
    private String groupCd;

    @Id
    private String code;

    private String name;

    private String useYn;

    private Integer sortSeq;

    private String attVal1;

    private String attVal2;

    private String attVal3;

    private String remarks;

    private LocalDateTime regDate;

    private LocalDateTime uptDate;

    private String regId;

    private String uptId;
}
