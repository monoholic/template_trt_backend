package kr.co.trito.service;

import kr.co.trito.domain.repository.mybatis.logAPI.LogAPIRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LogAPIService {

    private final LogAPIRepository logAPIRepository;

    // 메뉴 진입시 로그 추가
    public long addLog(String pagePath) {
        int res = logAPIRepository.addLog(pagePath);

        return (long)res;
    }
}
