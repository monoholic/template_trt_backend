package kr.co.trito.domain.repository.mybatis.comCodeJs;

import kr.co.trito.dto.Mybatis.comCode.ComCodeListDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ComCodeJsRepository {
    // 공통코드 opt 불러오기
    List<ComCodeListDto> getComCodeJsOpt(String codeGrpId);
}
