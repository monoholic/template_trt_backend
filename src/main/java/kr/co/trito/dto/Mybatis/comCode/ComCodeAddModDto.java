package kr.co.trito.dto.Mybatis.comCode;

import lombok.Data;

@Data
public class ComCodeAddModDto {
    private String codeId;
    private String codeGrpId;
    private String codeNm;
    private String codeEngNm;
    private String uppCodeId;
    private String codeDesc;
    private String codeLvl;
    private String sortOdr;
    private String resv1;
    private String resv2;
    private String resv3;
    private String useYn;
    private String addMod;
}
