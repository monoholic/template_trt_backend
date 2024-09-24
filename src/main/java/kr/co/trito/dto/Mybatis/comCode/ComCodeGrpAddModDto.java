package kr.co.trito.dto.Mybatis.comCode;

import lombok.Data;

@Data
public class ComCodeGrpAddModDto {
    private String codeGrpId;
    private String codeGrpNm;
    private String codeGrpEngNm;
    private String codeGrpDesc;
    private String sortOrd;
    private String useYn;
    private String addMod;
}
