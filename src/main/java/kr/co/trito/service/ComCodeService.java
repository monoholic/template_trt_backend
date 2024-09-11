package kr.co.trito.service;

import kr.co.trito.domain.repository.mybatis.comCode.ComCodeRepository;
import kr.co.trito.dto.Mybatis.comCode.ComCodeGrpListDto;
import kr.co.trito.dto.Mybatis.comCode.ComCodeGrpListParamsDto;
import kr.co.trito.dto.Mybatis.comCode.ComCodeGrpParmsDto;
import kr.co.trito.utils.SearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ComCodeService {

    @Autowired
    ComCodeRepository comCodeRepository;

    public Object getComCodeGrpList(ComCodeGrpListParamsDto comCodeGrpListParamsDto) {

        // sortBy를 DB 컬럼 양식으로 변환
        if(comCodeGrpListParamsDto.getSortBy() != null){
            String str = comCodeGrpListParamsDto.getSortBy().replaceAll("([a-z])([A-Z]+)", "$1_$2").toUpperCase();
            comCodeGrpListParamsDto.setSortBy(str);
        }

        // 검색 조건을 DB 컬럼 양식으로 변환
        if(comCodeGrpListParamsDto.getSearchOpt() != null){
            String str = comCodeGrpListParamsDto.getSearchOpt().replaceAll("([a-z])([A-Z]+)", "$1_$2").toUpperCase();
            comCodeGrpListParamsDto.setSearchOpt(str);
        }

        // 페이지 세팅
        SearchCondition condition = new SearchCondition(comCodeGrpListParamsDto.getPage(), comCodeGrpListParamsDto.getNumOfRows());
        int total = comCodeRepository.selectCountGrpCode(comCodeGrpListParamsDto);
        condition.pageSetup(total);

        // DB에 보낼 쿼리 파라미터
        ComCodeGrpParmsDto params = new ComCodeGrpParmsDto();
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
}
