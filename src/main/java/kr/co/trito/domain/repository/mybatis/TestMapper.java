package kr.co.trito.domain.repository.mybatis;

import kr.co.trito.dto.TestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface TestMapper {
   List<TestDTO> selectUser();
}
