package kr.co.trito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import kr.co.trito.domain.response.TritoResponse;
import kr.co.trito.dto.Mybatis.excel.ExcelListParamDto;
import kr.co.trito.exception.ExcelException;
import kr.co.trito.service.ExcelService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/excel")

public class ExcelController {
    @Autowired
    private final ExcelService excelService;

    // 조회
    @PostMapping("/list")
    public ResponseEntity<TritoResponse<?>> getExcelList(
            @Valid @RequestBody ExcelListParamDto excelListParamDto
    ) {
        return ResponseEntity.ok(new TritoResponse<>(excelService.getExcelList(excelListParamDto)));
    }

    // excel upload
    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) throws java.io.IOException, ExcelException {
        try {
            excelService.uploadExcelFile(file);
            return ResponseEntity.ok("Data uploaded successfully");
        } catch (ExcelException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // excel download
    @PostMapping("/download")
    public ResponseEntity<TritoResponse<?>> getDownList(
            @Valid @RequestBody ExcelListParamDto excelListParamDto
    ) {
        return ResponseEntity.ok(new TritoResponse<>(excelService.getDownList(excelListParamDto)));
    }
}
