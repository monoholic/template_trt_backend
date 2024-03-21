package kr.co.trito.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

import java.sql.Date;

@Entity
@IdClass(AllowancePK.class)
@Table(name = "TBPY_ALLOWANCE")
public class Allowance  {
   @Id
   private String sawonNo;

   @Id
   private String startDt;

   private Date endDt;

   private String reqGbn;

   private String reason;

   private String approvalId;

   private Date approvalDt;

   private String approvalStatus;

   private String approvalRemarks;

   private Integer amount;

   private Date regDt;

   private Date uptDt;

   private String regId;

   private String uptId;

   private String acceptIp;
}
