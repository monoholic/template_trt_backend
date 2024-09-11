package kr.co.trito.domain.repository.mybatis.comCode;

import kr.co.trito.dto.Mybatis.comCode.ComCodeGrpListDto;
import kr.co.trito.dto.Mybatis.comCode.ComCodeGrpListParamsDto;
import kr.co.trito.dto.Mybatis.comCode.ComCodeGrpParmsDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ComCodeRepository {
    int selectCountGrpCode(ComCodeGrpListParamsDto comCodeGrpListParamsDto);

    List<ComCodeGrpListDto> getComCodeGrpList(ComCodeGrpParmsDto params);
}
