package kr.co.trito.domain.repository.mybatis.logAPI;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogAPIRepository {

    // 메뉴 진입시 로그 추가
    int addLog(String pagePath);
}
