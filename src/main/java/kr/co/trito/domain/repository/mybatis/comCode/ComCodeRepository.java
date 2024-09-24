package kr.co.trito.domain.repository.mybatis.comCode;

import kr.co.trito.dto.Mybatis.comCode.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ComCodeRepository {

    // ------------------ 공통코드 그룹 관리 ---------------------------

    // 공통코드 그룹 total row
    int selectCountGrpCode(ComCodeGrpListParamsDto comCodeGrpListParamsDto);

    // 공통코드 그룹 목록 조회
    List<ComCodeGrpListDto> getComCodeGrpList(ComCodeGrpParamsDto params);

    // 공통코드 그룹 ID 중복체크
    int checkComCodeGrpId(String codeGrpId);

    // 공통코드 그룹 추가
    int addComCodeGrp(ComCodeGrpAddModDto comCodeGrpAddModDto);

    // 공통코드 그룹 수정
    int modComCodeGrp(ComCodeGrpAddModDto comCodeGrpAddModDto);

    // 공통코드 그룹 삭제
    int delComCodeGrp(List<String> params);

    // ------------------ 공통코드 관리 ---------------------------

    // 공통코드 total row
    int selectCountCode(ComCodeListParamDto comCodeListParamDto);

    // 공통코드 목록 조회
    List<ComCodeListDto> getComCodeList(ComCodeParamsDto params);

    // 공통코드 ID 중복체크
    int checkComCodeId(ComCodeAddModDto comCodeAddModDto);

    // 공통코드 추가
    int addComCode(ComCodeAddModDto comCodeAddModDto);

    // 공통코드 수정
    int modComCode(ComCodeAddModDto comCodeAddModDto);

    // 공통코드 삭제
    int delComCode(Object params);
}
