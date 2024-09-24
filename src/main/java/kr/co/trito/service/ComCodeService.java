package kr.co.trito.service;

import kr.co.trito.domain.repository.mybatis.comCode.ComCodeRepository;
import kr.co.trito.dto.Mybatis.comCode.*;
import kr.co.trito.exception.ComCodeException;
import kr.co.trito.utils.ComUtil;
import kr.co.trito.utils.SearchCondition;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kr.co.trito.enums.ComCodeErrorCode.CODE_GRP_ID_DUPLICATION;
import static kr.co.trito.enums.ComCodeErrorCode.CODE_ID_DUPLICATION;

@Service
@Transactional
@AllArgsConstructor
public class ComCodeService {

    @Autowired
    private final ComCodeRepository comCodeRepository;

    // ------------------ 공통코드 그룹 관리 ---------------------------

    // 공통코드 그룹 목록 조회
    public Object getComCodeGrpList(ComCodeGrpListParamsDto comCodeGrpListParamsDto) {

        ComUtil comUtil = new ComUtil();

        // sortBy를 DB 컬럼 양식으로 변환
        if(comCodeGrpListParamsDto.getSortBy() != null){
            comCodeGrpListParamsDto.setSortBy(comUtil.changeForm(comCodeGrpListParamsDto.getSortBy()));
        }

        // 검색 조건을 DB 컬럼 양식으로 변환
        if(comCodeGrpListParamsDto.getSearchOpt() != null){
            comCodeGrpListParamsDto.setSearchOpt(comUtil.changeForm(comCodeGrpListParamsDto.getSearchOpt()));
        }

        // 페이지 세팅
        SearchCondition condition = new SearchCondition(comCodeGrpListParamsDto.getPage(), comCodeGrpListParamsDto.getNumOfRows());
        int total = comCodeRepository.selectCountGrpCode(comCodeGrpListParamsDto);
        condition.pageSetup(total);

        // DB에 보낼 쿼리 파라미터
        ComCodeGrpParamsDto params = new ComCodeGrpParamsDto();
        params.setCondition(condition);
        params.setComCodeGrpListParamsDto(comCodeGrpListParamsDto);

        // 목록 조회
        List<ComCodeGrpListDto> resList = comCodeRepository.getComCodeGrpList(params);

        // 반환 값
        Map<String, Object> res = new HashMap<>();

        res.put("resList", resList);
        res.put("total", total);

        return res;
    }

    // 공통코드 그룹 추가, 수정
    public long addModComCodeGrp(ComCodeGrpAddModDto comCodeGrpAddModDto) {
        int res = -1;

        // 추가, 수정 분기
        String flag = comCodeGrpAddModDto.getAddMod();
        
        if(flag.equals("A")) res = addComCodeGrp(comCodeGrpAddModDto);
        else res = modComCodeGrp(comCodeGrpAddModDto);
        
        return (long)res;
    }

    // 공통코드 그룹 추가
    public int addComCodeGrp(ComCodeGrpAddModDto comCodeGrpAddModDto){
        int res = -1;

        // 공통코드 그룹 ID 중복 체크
        int idCnt = comCodeRepository.checkComCodeGrpId(comCodeGrpAddModDto.getCodeGrpId());

        if(idCnt == 0){
            // 중복X
            res = comCodeRepository.addComCodeGrp(comCodeGrpAddModDto);
        } else {
            // 중복
            throw new ComCodeException(CODE_GRP_ID_DUPLICATION);
        }

        return res;
    }

    // 공통코드 그룹 수정
    private int modComCodeGrp(ComCodeGrpAddModDto comCodeGrpAddModDto) {
        int res = -1;
        res = comCodeRepository.modComCodeGrp(comCodeGrpAddModDto);

        return res;
    }

    // 공통코드 그룹 삭제
    public long delComCodeGrp(List<String> params) {
        int res = -1;
        res = comCodeRepository.delComCodeGrp(params);

        return (long)res;
    }

    // ------------------ 공통코드 관리 ---------------------------

    // 공통코드 목록 조회
    public Object getComCodeList(ComCodeListParamDto comCodeListParamDto) {

        ComUtil comUtil = new ComUtil();

        // sortBy를 DB 컬럼 양식으로 변환
        if(comCodeListParamDto.getSortBy() != null){
            comCodeListParamDto.setSortBy(comUtil.changeForm(comCodeListParamDto.getSortBy()));
        }

        // 검색 조건을 DB 컬럼 양식으로 변환
        if(comCodeListParamDto.getSearchOpt() != null){
            comCodeListParamDto.setSearchOpt(comUtil.changeForm(comCodeListParamDto.getSearchOpt()));
        }

        // 페이지 세팅
        SearchCondition condition = new SearchCondition(comCodeListParamDto.getPage(), comCodeListParamDto.getNumOfRows());
        int total = comCodeRepository.selectCountCode(comCodeListParamDto);
        condition.pageSetup(total);

        // DB에 보낼 쿼리 파라미터
        ComCodeParamsDto params = new ComCodeParamsDto();
        params.setCondition(condition);
        params.setComCodeListParamDto(comCodeListParamDto);

        // 목록 조회
        List<ComCodeListDto> resList = comCodeRepository.getComCodeList(params);

        // 반환 값
        Map<String, Object> res = new HashMap<>();

        res.put("resList", resList);
        res.put("total", total);

        return res;
    }

    // 공통코드 추가, 수정
    public Object addModComCode(ComCodeAddModDto comCodeAddModDto) {
        int res = -1;

        // 추가, 수정 분기
        String flag = comCodeAddModDto.getAddMod();

        if(flag.equals("A")) res = addComCode(comCodeAddModDto);
        else res = modComCode(comCodeAddModDto);

        return (long)res;
    }

    // 공통코드 추가
    private int addComCode(ComCodeAddModDto comCodeAddModDto) {
        int res = -1;

        // 공통코드 ID 중복 체크
        int idCnt = comCodeRepository.checkComCodeId(comCodeAddModDto);

        if(idCnt == 0){
            // 중복X
            res = comCodeRepository.addComCode(comCodeAddModDto);
        } else {
            // 중복
            throw new ComCodeException(CODE_ID_DUPLICATION);
        }

        return res;
    }

    // 공통코드 수정
    private int modComCode(ComCodeAddModDto comCodeAddModDto) {
        int res = -1;
        res = comCodeRepository.modComCode(comCodeAddModDto);

        return res;
    }

    // 공통코드 삭제
    public Object delComCode(Object params) {
        int res = -1;
        res = comCodeRepository.delComCode(params);

        return (long)res;
    }

}
