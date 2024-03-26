package kr.co.trito.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@IdClass(WorkTimePK.class)
@Table(name = "TBPY_WORKTIME")
public class WorkTime {
    @Id
    private String sawonNo;

    @Id
    private LocalDateTime startDt;

    private LocalDateTime endDt;

    private String gubunCd;

    private String remarks;

    private String startIp;

    private String endIp;

    private String cause;
}
