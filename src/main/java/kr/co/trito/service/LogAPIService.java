package kr.co.trito.service;

import kr.co.trito.domain.repository.mybatis.logAPI.LogAPIRepository;
import kr.co.trito.dto.Mybatis.logAPI.LogAPIListDto;
import kr.co.trito.dto.Mybatis.logAPI.LogAPIListParamsDto;
import kr.co.trito.dto.Mybatis.logAPI.LogAPIParamsDto;
import kr.co.trito.utils.ComUtil;
import kr.co.trito.utils.SearchCondition;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Transactional
public class LogAPIService {

    private final LogAPIRepository logAPIRepository;

    // 메뉴 진입시 로그 추가
    public long addLog(String pagePath) {
        int res = logAPIRepository.addLog(pagePath);

        return (long)res;
    }

    // 로그 관리 조회
    public Object getLogList(LogAPIListParamsDto logAPIListParamsDto) {

        ComUtil comUtil = new ComUtil();

        // sortBy를 DB 컬럼 양식으로 변환
        if(logAPIListParamsDto.getSortBy() != null){
            logAPIListParamsDto.setSortBy(comUtil.changeForm(logAPIListParamsDto.getSortBy()));
        }

        // 페이지 관련 세팅
        SearchCondition condition = new SearchCondition(logAPIListParamsDto.getPage(), logAPIListParamsDto.getNumOfRows());
        int total = logAPIRepository.CountLogAPIList(logAPIListParamsDto);
        condition.pageSetup(total);

        // DB에 보낼 쿼리 파라미터
        LogAPIParamsDto params = new LogAPIParamsDto();
        params.setCondition(condition);
        params.setLogAPIListParamsDto(logAPIListParamsDto);

        // 목록 조회
        List<LogAPIListDto> resList = logAPIRepository.getLogAPIList(params);

        // 반환 값
        Map<String, Object> res = new HashMap<>();

        res.put("resList", resList);
        res.put("total", total);

        return res;
    }
}
