package kr.co.trito.domain.repository.mybatis.excel;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.trito.dto.Mybatis.excel.ExcelListDto;
import kr.co.trito.dto.Mybatis.excel.ExcelListParamDto;
import kr.co.trito.dto.Mybatis.excel.ExcelParamDto;
import kr.co.trito.dto.Mybatis.excel.ExcelUpListDto;

@Mapper
public interface ExcelTemRepository {
    
    int selectCountLog(ExcelListParamDto excelListParamDto);
    
    List<ExcelListDto> getExcelList(ExcelParamDto params);
    
    List<ExcelListDto> getDownList(ExcelParamDto params);
    
    void uploadExcel(List<ExcelUpListDto> dataList);
    
}
