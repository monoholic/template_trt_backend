package kr.co.trito.service;

import kr.co.trito.domain.repository.mybatis.comCodeJs.ComCodeJsRepository;
import kr.co.trito.dto.Mybatis.comCode.ComCodeListDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ComCodeJsService {

    private final ComCodeJsRepository comCodeJsRepository;

    // 공통코드 opt 불러오기
    public List<ComCodeListDto> getComCodeJsOpt(String codeGrpId) {
        return comCodeJsRepository.getComCodeJsOpt(codeGrpId);
    }
}
