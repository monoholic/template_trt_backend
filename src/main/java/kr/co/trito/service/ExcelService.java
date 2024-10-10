package kr.co.trito.service;

import static kr.co.trito.enums.ExcelErrorCode.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.co.trito.domain.repository.mybatis.excel.ExcelTemRepository;
import kr.co.trito.dto.Mybatis.excel.ExcelListDto;
import kr.co.trito.dto.Mybatis.excel.ExcelListParamDto;
import kr.co.trito.dto.Mybatis.excel.ExcelParamDto;
import kr.co.trito.dto.Mybatis.excel.ExcelUpListDto;
import kr.co.trito.exception.ExcelException;
import kr.co.trito.utils.SearchCondition;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ExcelService {

    @Autowired
    private final ExcelTemRepository excelTemRepository;
        
    // 조회
    public Object getExcelList(ExcelListParamDto excelListParamDto) {

        if(excelListParamDto.getSortBy() != null){
            String str = excelListParamDto.getSortBy().replaceAll("([a-z])([A-Z]+)", "$1_$2").toUpperCase();
            excelListParamDto.setSortBy(str);
        }

        SearchCondition condition = new SearchCondition(excelListParamDto.getPage(), excelListParamDto.getNumOfRows());
        int total = excelTemRepository.selectCountLog(excelListParamDto);
        condition.pageSetup(total);

        ExcelParamDto params = new ExcelParamDto();
        params.setCondition(condition);
        params.setExcelListParamDto(excelListParamDto);

        List<ExcelListDto> resList = excelTemRepository.getExcelList(params);

        Map<String, Object> res = new HashMap<>();

        res.put("resList", resList);
        res.put("total", total);

        return res;
    }

    // excel upload
    public void uploadExcelFile(MultipartFile file) throws IOException,  ExcelException{

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
            List<ExcelUpListDto> dataList = new ArrayList<>();
            DataFormatter formatter = new DataFormatter();
            
            for (org.apache.poi.ss.usermodel.Row row : sheet) {
                if(row.getRowNum() != 0) {
                    ExcelUpListDto data = new ExcelUpListDto();
                
                    data.setSawonNo(formatter.formatCellValue(row.getCell(0)));
                    data.setSawonNm(row.getCell(1).getStringCellValue());
                    data.setStartDate(formatter.formatCellValue(row.getCell(2)));
                    data.setStartTime(formatter.formatCellValue(row.getCell(3)));
                    data.setEndTime(formatter.formatCellValue(row.getCell(4)));
                    data.setCause(row.getCell(5).getStringCellValue());

                    if(data.getSawonNo() == "" ){
                        throw new ExcelException(EXCEL_DATA_NULL);
                    }
                    if(data.getStartDate().length() != 8 || data.getSawonNo().length() != 7 || data.getStartTime().length() != 6 || data.getEndTime().length() != 6){
                        throw new ExcelException(EXCEL_DATA_LENGTH);
                    }

                    dataList.add(data);
                }
            }

            //dataList.remove(0);

            excelTemRepository.uploadExcel(dataList);
        }
    }

    // excel downlaod
    public Object getDownList(ExcelListParamDto excelListParamDto) {
        
        ExcelParamDto params = new ExcelParamDto();
        params.setExcelListParamDto(excelListParamDto);

        List<ExcelListDto> resList = excelTemRepository.getDownList(params);

        Map<String, Object> res = new HashMap<>();

        res.put("resList", resList);

        return res;
    }
}
