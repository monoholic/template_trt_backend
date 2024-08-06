package kr.co.trito.domain.repository.mybatis.workTime;

import kr.co.trito.dto.Mybatis.WorkTime.WorkTimeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface WorkTimeMapper {
   WorkTimeDto getWorkTime(String sawonNo);
}
