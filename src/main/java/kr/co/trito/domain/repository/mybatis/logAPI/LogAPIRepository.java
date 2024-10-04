package kr.co.trito.domain.repository.mybatis.logAPI;

import kr.co.trito.dto.Mybatis.logAPI.LogAPIListDto;
import kr.co.trito.dto.Mybatis.logAPI.LogAPIListParamsDto;
import kr.co.trito.dto.Mybatis.logAPI.LogAPIParamsDto;
import kr.co.trito.dto.Mybatis.logAPI.SqlLogDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LogAPIRepository {

    // 메뉴 진입시 로그 추가
    int addLog(String pagePath);

    // 로그 API 리스트 count
    int CountLogAPIList(LogAPIListParamsDto logAPIListParamsDto);

    // 로그 API 리스트 조회
    List<LogAPIListDto> getLogAPIList(LogAPIParamsDto params);

    // 로그 SQL문 삽입
    int addSql(Object params);
}
